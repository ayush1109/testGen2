package fetcher;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.similarity.LevenshteinDistance;

import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

public class LongestNodeDistance implements NodeDistance {
    private static final double POINTS_FOR_TAG = 100.0;
    private static final double POINTS_FOR_LCS = 100.0;
    private static final double POINTS_FOR_ID = 50.0;
    private static final double POINTS_FOR_CLASS = 40.0;
    private static final double POINTS_FOR_VALUE = 30.0;
    private static final double POINTS_FOR_INDEX = 0.0;
    private static final double POINTS_FOR_OTHER_ATTRIBUTE = 30.0;

    public LongestNodeDistance() {
    }

    public double distance(NodeInfo nodeInfo1, NodeInfo nodeInfo2, int LCSDistance, int curPathHeight) {
        double score = 0.0;
        if (curPathHeight != 0 && (curPathHeight <= 5 || !((double)LCSDistance / (double)curPathHeight < 0.7))) {
            score += (double)LCSDistance / (double)curPathHeight * 100.0;
            Set<String> propertyNames = GemUtils.union(nodeInfo1.getOtherAttributes().keySet(), nodeInfo2.getOtherAttributes().keySet());
            Set<String> classNames = GemUtils.union(nodeInfo1.getClasses(), nodeInfo2.getClasses());
            double maximumScore = 350.0;
            if (StringUtils.equalsIgnoreCase(nodeInfo1.getTag(), nodeInfo2.getTag())) {
                score += 100.0;
            }
            if (Objects.equals(nodeInfo1.getIndex(), nodeInfo2.getIndex())) {
                score += 0.0;
            }

            if (nodeInfo1.getId() != null && nodeInfo2.getId() != null) {
                score += 50.0 * this.calculateLevenshteinScore(nodeInfo1.getId(), nodeInfo2.getId(), 0.3);
            }

            score += 30.0 * this.calculateLevenshteinScore(nodeInfo1.getInnerText(), nodeInfo2.getInnerText(), 0.3);
            Set<String> classesIntersect = GemUtils.intersect(nodeInfo1.getClasses(), nodeInfo2.getClasses());
            double intersectScore = (double)classesIntersect.size() * 40.0;


//
            if (classNames.size() > 0) {
                intersectScore /= (double)classNames.size();
                score += intersectScore;
            } else {
                score += 40.0;
            }

            Set<String> node1classesDifference = GemUtils.difference(nodeInfo1.getClasses(), nodeInfo2.getClasses());
            Set<String> node2classesDifference = GemUtils.difference(nodeInfo2.getClasses(), nodeInfo1.getClasses());

            int lengthDifference = GemUtils.union(node1classesDifference, node2classesDifference).size();
            double otherAttributesScore;
            if (lengthDifference > 0) {
                otherAttributesScore = 0.0;
                if (node1classesDifference.size() > 0) {
                    otherAttributesScore = this.calculateClassesIntersectionByLevenshtein(node1classesDifference, nodeInfo2.getClasses());
                } else {
                    otherAttributesScore = this.calculateClassesIntersectionByLevenshtein(nodeInfo1.getClasses(), node2classesDifference);
                }

                if (classNames.size() > 0) {
                    otherAttributesScore /= (double)classNames.size();
                }

                score += (double)lengthDifference * 40.0 * otherAttributesScore;
            }

            otherAttributesScore = 0.0;

            String propertyName;
            for(Iterator var19 = propertyNames.iterator(); var19.hasNext(); otherAttributesScore += 30.0 * this.calculateLevenshteinScore((String) nodeInfo1.getOtherAttributes().get(propertyName), (String) nodeInfo2.getOtherAttributes().get(propertyName), 0.75)) {
                propertyName = (String)var19.next();
            }

            if (propertyNames.size() > 0) {
                otherAttributesScore /= (double)propertyNames.size();
                score += otherAttributesScore;
            } else {
                score += 30.0;
            }

            return score / maximumScore;
        } else {
            return 0.0;
        }
    }

    private double calculateClassesIntersectionByLevenshtein(Set<String> nodeClasses1, Set<String> nodeClasses2) {
        int comparisonsNumber = 0;
        double scores = 0.0;
        Iterator var6 = nodeClasses1.iterator();

        while(var6.hasNext()) {
            String classNameFirst = (String)var6.next();

            for(Iterator var8 = nodeClasses2.iterator(); var8.hasNext(); ++comparisonsNumber) {
                String classNameSecond = (String)var8.next();
                scores += this.calculateLevenshteinScore(classNameFirst, classNameSecond, 0.75);
            }
        }

        if (comparisonsNumber == 0) {
            return 0.0;
        } else {
            return scores / (double)comparisonsNumber;
        }
    }

    private double calculateLevenshteinScore(String innerText1, String innerText2, double thresholdPercent) {
        if (innerText1 != null && innerText2 != null) {
            innerText1 = innerText1.toLowerCase();
            innerText2 = innerText2.toLowerCase();
            int length = Math.max(innerText1.length(), innerText2.length());
            if (length == 0) {
                return 1.0;
            } else {
                int threshold = this.calculateLevenshteinThreshold(length, thresholdPercent);
                LevenshteinDistance levenshtein = new LevenshteinDistance(threshold);
                Integer distance = levenshtein.apply(innerText1, innerText2);
                return distance < 0 ? 0.0 : ((double)length - distance.doubleValue()) / (double)length;
            }
        } else {
            return 0.0;
        }
    }

    private int calculateLevenshteinThreshold(int maxTextLength, double thresholdPercent) {
        return (int)((double)maxTextLength * thresholdPercent + 1.0);
    }




}
