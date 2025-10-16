package io.loop.pages;

public class POM {

    private LoginPage loginPage;
    private HomePage homePage;
    private ReceivedDocsPage receivedDocsPage;
    private LeftNavigatePage leftNavigatePage;
    private MyUploadsPage myUploadsPage;
    private DocuportLoginPage  docuportLoginPage;
    private BaseDocuportPage baseDocuportPage;


    public BaseDocuportPage getBaseDocuportPage() {
        if (baseDocuportPage == null) {
            baseDocuportPage = new BaseDocuportPage();
        }
        return baseDocuportPage;
    }

    public DocuportLoginPage getDocuportLoginPage() {
        if(docuportLoginPage == null) {
            docuportLoginPage = new DocuportLoginPage();
        }
        return docuportLoginPage;
    }

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

    public HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage();
        }
        return homePage;
    }


}
