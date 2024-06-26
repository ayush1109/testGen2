package fetcher;

public class LCSNodePathDistance implements NodePathDistance {
    public LCSNodePathDistance() {
    }

    public int distance(NodePath nodePath1, NodePath nodePath2) {
        return this.lcs(nodePath1.getNodes(), nodePath2.getNodes());
    }

    private int lcs(NodeInfo[] X, NodeInfo[] Y) {
        int m = X.length;
        int n = Y.length;
        int[][] L = new int[m + 1][n + 1];

        for(int i = 0; i <= m; ++i) {
            for(int j = 0; j <= n; ++j) {
                if (i != 0 && j != 0) {
                    if (X[i - 1].equals(Y[j - 1])) {
                        L[i][j] = L[i - 1][j - 1] + 1;
                    } else {
                        L[i][j] = Math.max(L[i - 1][j], L[i][j - 1]);
                    }
                } else {
                    L[i][j] = 0;
                }
            }
        }

        return L[m][n];
    }
}

