module moduleTest.app.main {
    requires java.desktop;
    requires com.formdev.flatlaf;
    requires com.google.common;

    opens myPackage;
}