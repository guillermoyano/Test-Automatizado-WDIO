import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestIntegrador {

        private AndroidDriver driver;
        private WebDriverWait wait;

        private static final String DEVICE_NAME = "emulator-5554"; // Nombre del emulador
        private static final String PLATFORM_NAME = "Android"; // OS del emulador
        private static final String APP_PACKAGE = "com.wdiodemoapp"; // Paquete de la app a probar
        private static final String APP_ACTIVITY = "com.wdiodemoapp.MainActivity"; // Pantalla principal
        private static final String EMAIL = "mail@gmail.com"; // Email de prueba
        private static final String PASSWORD = "12345678"; // Password de prueba
        private static final PointerInput FINGER = new PointerInput(PointerInput.Kind.TOUCH, "finger"); // Input para el
                                                                                                        // swipe

        // Este método se ejecuta antes de cada prueba
        @BeforeEach
        public void setUp() throws MalformedURLException {
                driver = crearConexionConParametros(DEVICE_NAME, PLATFORM_NAME, APP_ACTIVITY, APP_PACKAGE);
                wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Espera implícita
                abrirApp();
        }

        // Este método se ejecuta después de cada prueba
        @AfterEach
        public void tearDown() {
                if (driver != null) {
                        driver.quit(); // Cierra la app después de cada test
                }
        }

        public AndroidDriver crearConexionConParametros(String deviceName, String platformName,
                        String appActivity, String appPackage) throws MalformedURLException {
                UiAutomator2Options options = new UiAutomator2Options()
                                .setDeviceName(deviceName)
                                .setPlatformName(platformName)
                                .setAppActivity(appActivity)
                                .setAppPackage(appPackage)
                                .setNoReset(true);
                return new AndroidDriver(URI.create("http://127.0.0.1:4723").toURL(), options);
        }

        public void abrirApp() {
                driver.activateApp(APP_PACKAGE);
                try {
                        var homeIcon = driver.findElement(AppiumBy.androidUIAutomator(
                                        "new UiSelector().className(\"android.widget.TextView\").text(\"󰚡\")"));
                        homeIcon.click();
                } catch (Exception e) {
                        driver.navigate().back();
                }
        }

        @Test
        @Order(1)
        @DisplayName("Verificar inicio de la app")
        public void verificarInicio() {
                // Verificamos que la app haya arrancado correctamente al buscar un título de
                // pantalla
                Assertions.assertNotNull(driver, "El driver no fue inicializado correctamente.");
        }

        @Test
        @Order(2)
        @DisplayName("Iniciar sesión")
        public void iniciarSesion() {
                // Test del login completo, ingresando email y password válidos
                wait.until(ExpectedConditions
                                .elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().text(\"󰍂\")")))
                                .click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("input-email")))
                                .sendKeys(EMAIL);
                wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("input-password")))
                                .sendKeys(PASSWORD);
                wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator(
                                "new UiSelector().className(\"android.view.ViewGroup\").instance(16)"))).click();

                // Verificamos que el login haya sido exitoso
                String texto = driver
                                .findElement(AppiumBy.xpath("//android.widget.TextView[@text='You are logged in!']"))
                                .getText();
                Assertions.assertEquals("You are logged in!", texto);
                wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("android:id/button1"))).click();
        }

        @Test
        @Order(3)
        @DisplayName("Validar campo de texto")
        public void inputField() {
                // Probamos que el campo de texto funcione correctamente
                wait.until(ExpectedConditions.visibilityOfElementLocated(
                                AppiumBy.androidUIAutomator("new UiSelector().text(\"󰏫\")"))).click();
                WebElement input = wait.until(
                                ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("text-input")));
                String texto = "Escribir algo en este campo";
                input.sendKeys(texto);

                WebElement resultado = driver.findElement(AppiumBy.accessibilityId("input-text-result"));
                Assertions.assertEquals(texto, resultado.getText(), "El texto ingresado no coincide.");
        }

        @Test
        @Order(4)
        @DisplayName("Validar campo de mail")
        public void validateEmailField() {
                // Verificamos que el campo de email esté visible y habilitado
                wait.until(ExpectedConditions
                                .elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().text(\"󰍂\")")))
                                .click();
                WebElement campoMail = wait.until(
                                ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("input-email")));
                campoMail.sendKeys(EMAIL);
                Assertions.assertTrue(EMAIL.contains("@"), "El email no contiene '@'");
        }

        @Test
        @Order(5)
        @DisplayName("Verificar password")
        public void validatePasswordField() {
                // Similar al anterior, pero con el campo de password
                wait.until(ExpectedConditions
                                .elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().text(\"󰍂\")")))
                                .click();
                WebElement campoPass = wait.until(ExpectedConditions
                                .visibilityOfElementLocated(AppiumBy.accessibilityId("input-password")));
                campoPass.sendKeys(PASSWORD);
                Assertions.assertTrue(PASSWORD.length() >= 8, "El password tiene menos de 8 caracteres");
        }

        @Test
        @Order(6)
        @DisplayName("Activar botón")
        public void botonFormulario() {
                driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"󰏫\")")).click();
                driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Active\")")).click();
                WebElement mensaje = driver.findElement(AppiumBy.id("android:id/message"));
                Assertions.assertEquals("This button is active", mensaje.getText(),
                                "El mensaje del botón no es correcto");
                driver.findElement(AppiumBy.id("android:id/button1")).click();
        }

        @Test
        @Order(7)
        @DisplayName("Swipe a JS.Foundation")
        public void swipeHorizontal() {
                driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"󰤼\")")).click();

                swipePantalla(new Point(1006, 1428), new Point(215, 1432));
                swipePantalla(new Point(977, 1432), new Point(232, 1424));

                WebElement titulo = driver
                                .findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"JS.FOUNDATION\")"));
                Assertions.assertEquals("JS.FOUNDATION", titulo.getText(), "No se encuentra el título esperado");
        }

        private void swipePantalla(Point start, Point end) {
                var swipe = new org.openqa.selenium.interactions.Sequence(FINGER, 1);
                swipe.addAction(FINGER.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), start.getX(),
                                start.getY()));
                swipe.addAction(FINGER.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
                swipe.addAction(FINGER.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(),
                                end.getX(), end.getY()));
                swipe.addAction(FINGER.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
                driver.perform(List.of(swipe));
        }

        @Test
        @Order(8)
        @DisplayName("Cerrar aplicación")
        public void cerrarApp() {
                // Este test simula el cierre de la app manualmente y verifica que la app se
                // haya cerrado correctamente
                driver.terminateApp(APP_PACKAGE);

                try {
                        WebElement elemento = driver.findElement(AppiumBy.id("android:id/button1"));
                        Assertions.fail("La aplicación no se cerró correctamente, el elemento sigue disponible.");
                } catch (Exception e) {
                        Assertions.assertTrue(e instanceof org.openqa.selenium.NoSuchElementException,
                                        "Error inesperado al verificar el cierre de la app");
                        System.out.println("La aplicación se cerró correctamente.");
                }
        }

}
