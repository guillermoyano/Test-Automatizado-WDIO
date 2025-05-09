# 📱 Proyecto de Automatización Mobile - App WDIO Demo con Appium

Este proyecto implementa pruebas automatizadas sobre la aplicación móvil **WebDriverIO Demo App (v1.0.8)**, diseñada para el aprendizaje y la práctica de automatización móvil. Las pruebas fueron desarrolladas en **Java** utilizando **Appium**, **JUnit**, y ejecutadas sobre un **emulador Android** (Pixel 7) desde **Android Studio**.

🧑‍💻 Autor
Guillermo Román Moyano
Estudiante de QA Automation, con formación en backend Java y pasión por la calidad de software, la automatización y el aprendizaje continuo.

---

## 🚀 Tecnologías y Herramientas

- **Lenguaje:** Java (JDK 21)
- **Framework de automatización:** Appium 9.3.0
- **Frameworks de testing:** JUnit 5, TestNG (opcional), JUnit 4 (por compatibilidad)
- **Herramienta de build:** Maven
- **Emulador:** Android Emulator (Pixel 7)
- **Inspección de elementos:** Appium Inspector
- **ADB & Android SDK**
- **Motor de automatización:** UiAutomator2
- **IDE:** NetBeans 21

---

## 📦 Estructura del Proyecto

- `src/test/java/...` → Contiene las clases de prueba
- `pom.xml` → Define las dependencias necesarias
- `app/` → Carpeta recomendada para guardar el archivo `.apk`
- `README.md` → Este archivo de documentación

---

## 🔧 Configuración del Entorno

1. **Instalar JDK 21 o superior**
2. **Instalar Android Studio y configurar el emulador** (se recomienda Pixel 7)
3. **Instalar Appium (versión 2) y Appium Inspector**
4. Iniciar el servidor de Appium:
   ```bash
   appium server --address 127.0.0.1 --port 4723 --base-path /



5. **Instalar la aplicación WDIO Demo** desde:
[🔗 WebDriverIO Native Demo App - Releases](https://github.com/webdriverio/native-demo-app/releases)

6. **Clonar este repositorio y abrirlo con NetBeans o tu IDE preferido**

---

## ✅ Escenarios Automatizados

| Nº | Escenario                                                 | Resultado Esperado                                 | Estado |
|----|-----------------------------------------------------------|----------------------------------------------------|--------|
| 1  | Inicio correcto de la app                                 | Muestra pantalla principal                         | ✓      |
| 2  | Login exitoso con email y contraseña                      | Mensaje: "You are logged in!"                      | ✓      |
| 3  | Validación de campo de email                              | Visible y editable                                 | ✓      |
| 4  | Validación de campo de contraseña                         | Visible y editable                                 | ✓      |
| 5  | Ingreso de texto en formulario                            | El texto se conserva                               | ✓      |
| 6  | Botón del formulario clickeable                           | Se puede presionar                                 | ✓      |
| 7  | Mensaje tras enviar formulario                            | Aparece mensaje de confirmación                    | ✓      |
| 8  | Swipe horizontal hasta encontrar texto "COMPATIBLE"       | El texto se visualiza correctamente                | ✓      |
| 9  | Cierre correcto de la app                                 | La app se cierra sin errores                       | ✓      |
| 10 | Flujo completo de login y navegación entre secciones      | Todo el recorrido funciona sin fallos              | ✓      |



## 🧪 Ejecución de las Pruebas

1. Asegurate de que el emulador esté encendido y Appium corriendo.  
2. Ejecutá las pruebas desde el IDE o con Maven:
   ```bash
    mvn test

---

## 📝 Buenas Prácticas Aplicadas

- Uso de aserciones claras para validar el comportamiento esperado.  
- Separación de lógica por métodos reutilizables.  
- Simulación de flujo completo del usuario en la aplicación.  
- Pruebas independientes y controladas.  
- Planeado para futura implementación de Page Object Model (POM).  

---

## 📹 Demostración en Video

▶️ [Ir al video de demostración en YouTube](https://youtu.be/5Lbit5e1TSg)

El video incluye:

- Inicio de Appium  
- Lanzamiento del emulador  
- Ejecución de los test  
- Resultados en consola  
- Comprobación visual de la automatización  


💡 Lecciones Aprendidas
Durante este proyecto aprendí a integrar Appium con un emulador Android, automatizar flujos comunes de prueba mobile, validar comportamientos esperados con aserciones, y mejorar la estructura de código con métodos reutilizables. También entendí la importancia de la sincronización y la robustez de los test automatizados.

📌 Mejoras Futuras
Reemplazo de localizadores repetidos por constantes reutilizables.

Refactorización hacia Page Object Model (POM).

Integración con herramientas de CI (GitHub Actions, Jenkins).

Validación en dispositivos físicos reales.

📁 Archivo pom.xml (dependencias clave)
   ```bash
   <dependency>
       <groupId>io.appium</groupId>
       <artifactId>java-client</artifactId>
       <version>9.3.0</version>
   </dependency>
   <dependency>
       <groupId>org.junit.jupiter</groupId>
       <artifactId>junit-jupiter-api</artifactId>
       <version>5.8.2</version>
       <scope>test</scope>
   </dependency>
   <!-- ...otras dependencias útiles -->

