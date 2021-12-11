module moduleTest.app.main {
    requires com.formdev.flatlaf;
    requires java.desktop;
    opens moduleTest to com.formdev.flatlaf;
}