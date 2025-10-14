package io.loop.pages;

public class POM {

    private LoginPage loginPage;
    private ProductPage productPage;
    private HomePage homePage;
    private ReceivedDocsPage receivedDocsPage;
    private LeftNavigatePage leftNavigatePage;
    private MyUploadsPage myUploadsPage;

    public MyUploadsPage getMyUploadsPage() {
        if (myUploadsPage == null){
            myUploadsPage = new MyUploadsPage();
        }
        return myUploadsPage;
    }


    public LeftNavigatePage getLeftNavigatePage() {
        if (leftNavigatePage == null) {
            leftNavigatePage = new LeftNavigatePage();
        }
        return leftNavigatePage;
    }

    public ReceivedDocsPage getReceivedDocsPage() {
        if (receivedDocsPage == null) {
            receivedDocsPage = new ReceivedDocsPage();
        }
        return receivedDocsPage;
    }

    public LoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    public ProductPage getProductPage() {
        if (productPage == null) {
            productPage = new ProductPage();
        }
        return productPage;
    }

    public HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage();
        }
        return homePage;
    }


}
