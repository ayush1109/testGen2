package fetcher;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class DOMFetcher {
    private WebDriver webDriver;

    public DOMFetcher(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public String getPageSource() {
        if (webDriver instanceof JavascriptExecutor) {
            Object pageSourceObj = ((JavascriptExecutor) webDriver).executeScript(
                    "return document.body.outerHTML;", new Object[0]);
            if (pageSourceObj != null) {
                return pageSourceObj.toString();
            }
        }

        return webDriver.getPageSource();
    }


}

