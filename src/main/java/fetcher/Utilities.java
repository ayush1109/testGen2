package fetcher;


import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.annotation.Nullable;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

;

public class Utilities {

    public static List<NodeInfo> list;

    public static NodeInfo parseTree(String tree) {
        return (new JsoupHTMLParser()).parse(new ByteArrayInputStream(tree.getBytes(StandardCharsets.UTF_8)));
    }

    public static void findUrl(String url) {
        try {
            // Parse the URL
            URI uri = new URI(url);

            // Get the host (site name) from the URI
            String siteName = uri.getHost();
            if (siteName == null) siteName = uri.getPath();

            // Remove the "www." prefix if it exists
            if (siteName != null && siteName.startsWith("www.")) {
                siteName = siteName.substring(4);
            } else if (StringUtils.containsIgnoreCase(siteName, "html")) {
                String[] name = siteName.split("/");
                siteName = name[name.length - 1];
            }
            else if(StringUtils.containsIgnoreCase(siteName, "html")) {
                String[] name = siteName.split("/");
                siteName = name[name.length - 1];
            }

            // Split the site name by dots and take the first part
            String[] parts = siteName.split("\\.");
            if (parts.length > 0) {
                siteName = parts[0];
            }

//            System.out.println("Site Name: " + siteName);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static void createJsonFile(String fileName, List<NodeInfo> data, String xpathValue) throws IOException {
        String fileNameToCreate = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "java" + File.separator + "gemheal" + File.separator + fileName;
        File file = new File(fileNameToCreate + ".json");

        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();

        if (file.exists()) {
            JSONArray res = readFullJsonFile(fileName);
            Gson gson = new Gson();
            for (int i = 0; i < res.length(); i++) {
                JSONObject jsonObject3 = new JSONObject();
                String locator = res.getJSONObject(i).get("xpath").toString();
                if (StringUtils.equalsIgnoreCase(xpathValue, locator)) continue;
                jsonObject3.put("xpath", locator);
                JSONArray aa = res.getJSONObject(i).getJSONArray("nodes");
                List<NodeInfo> nodeInfoList = new ArrayList<>();
                for (int j = 0; j < aa.length(); j++) {
                    NodeInfo obj = gson.fromJson(aa.get(j).toString(), NodeInfo.class);
                    nodeInfoList.add(obj);

                }
                jsonObject3.put("nodes", nodeInfoList);
                jsonArray.put(jsonObject3);
            }

        }


        jsonObject.put("xpath", xpathValue);
        jsonObject.put("nodes", data);
        jsonArray.put(jsonObject);
        FileUtils.writeStringToFile(file, jsonArray.toString());
    }

    public static JSONArray readJsonFile(@Nullable String fileName, String xpathValue) throws Exception {
        String fileNameToRead = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "java" + File.separator + "gemheal" + File.separator + fileName;
        File file = new File(fileNameToRead + ".json");
        String fileContent = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        JSONArray jsonArray = new JSONArray(fileContent);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject object = (JSONObject) jsonArray.get(i);
            if (StringUtils.equalsIgnoreCase(xpathValue, (CharSequence) object.get("xpath"))) {
                return (JSONArray) object.get("nodes");
            }
        }
        return null;
    }

    public static String readProperties(String property) throws IOException { // Function to read Data from Properties File
        FileReader read = new FileReader(System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties");
        Properties credential = new Properties();
        credential.load(read);
        return credential.getProperty(property);
    }

    private String extractWebsiteName(String websiteUrl) {
        try {
            URL url = new URL(websiteUrl);
            String host = url.getHost();

            // Remove "www." if present
            if (host.startsWith("www.")) {
                host = host.substring(4);
            }

            return host;
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Invalid website URL: " + websiteUrl, e);
        }
    }

    public static JSONArray readFullJsonFile(String fileName) throws IOException {
        String fileNameToRead = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "java" + File.separator + "gemheal" + File.separator + fileName;
        File file = new File(fileNameToRead + ".json");
        String fileContent = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        JSONArray jsonArray = new JSONArray(fileContent);

        return jsonArray;
    }
}