#  PetCare

App móvil Android para registrar mascotas y llevar el control de sus cuidados (vacunas, desparasitación, alimentación, paseos, baños y citas al veterinario) con recordatorios.



##  Integrante

| ALEJANDRO VICENTE VELASQUEZ TORO 
| Rol principal | DSY1105 SECCION 005D
|---|---|
 Tema, navegación, Home, Detalle, animaciones |
 Login/Registro, formularios, validaciones, Room, recursos nativos |

**Equipo N°:** _por completar_ · **Tablero Trello:** _pegar enlace aquí_

##  Problemática

Quienes tienen mascotas olvidan con frecuencia fechas clave (vacunas, desparasitaciones, citas) y no tienen un lugar único donde guardar la información de cada animal. PetCare centraliza los datos de cada mascota y avisa cuando se acerca un cuidado.

##  Funcionalidades implementadas

- Splash animado y sesión persistente (si ya iniciaste sesión, entras directo).
- Registro e inicio de sesión con formularios validados.
- CRUD de mascotas: nombre, especie, raza, fecha de nacimiento, peso y foto.
- Cuidados por mascota (vacuna, desparasitación, alimentación, paseo, veterinario, baño) con fecha, hora y notas.
- Pestañas **Pendientes / Historial** y marcado de cuidados como completados.
- Pantalla global de **Recordatorios** con vencidos resaltados.
- **Notificaciones** programadas en la fecha y hora del cuidado.
- Modo oscuro / claro (persistente) y activación de recordatorios desde el perfil.
- Atajo a **veterinarias cercanas** (abre la app de mapas).

## Cumplimiento de la rúbrica

|---|---|
| Diseño visual con Material 3 | `ui/theme/Theme.kt`, componentes M3 (`Scaffold`, `NavigationBar`, `ModalBottomSheet`, `DatePicker`, `TimePicker`, `ExposedDropdownMenu`...) |
| Formularios validados con íconos y mensajes | `ui/components/FormComponents.kt` (`AppTextField`, `PasswordField`, `DropdownField`); formularios en `LoginScreen`, `RegisterScreen`, `PetFormScreen`, `PetDetailScreen` (cuidados) |
| Validaciones desde la lógica | `domain/Validators.kt` (sin dependencias de UI) + estados en los ViewModel; probadas en `app/src/test/.../ValidatorsTest.kt` |
| Navegación funcional | `ui/navigation/AppNavHost.kt` (Navigation Compose + barra inferior + argumentos) |
| Gestión de estado | `StateFlow` en ViewModels + `collectAsStateWithLifecycle` + `remember`/`rememberSaveable` |
| Almacenamiento local | **Room** (`data/local`) para usuarios, mascotas y cuidados; **DataStore** (`data/prefs`) para sesión y preferencias |
| Recursos nativos (≥ 2) | **Cámara** (foto de la mascota, con permiso en tiempo de ejecución) y **Notificaciones** (WorkManager + permiso Android 13+). Extra: intent a mapas |
| Animaciones | Splash (spring + fade), entrada de tarjetas, mensajes de error (`AnimatedVisibility`), cambio de color de cuidados, check de éxito, transiciones de navegación, barra inferior animada |
| Estructura modular + MVVM | Ver arquitectura abajo |
| GitHub + Trello | Ver `docs/GUIA_EQUIPO.md` |

```
app/src/main/java/cl/petcare/app/
├── data/
│   ├── local/        Room: entidades, DAOs, base de datos
│   ├── prefs/        DataStore: sesión, tema, notificaciones
│   └── repository/   Repositorios (única fuente de datos para los ViewModel)
├── domain/           Validators (reglas de negocio) y catálogos
├── notifications/    WorkManager + canal y notificaciones
├── di/               Contenedor de dependencias manual
├── util/             Formatos de fecha, fotos (FileProvider), intents
├── viewmodel/        Estado de cada pantalla (StateFlow)
└── ui/
    ├── theme/        Tema Material 3 (claro/oscuro)
    ├── components/   Componentes reutilizables
    ├── navigation/   NavHost y rutas
    └── screens/      Splash, Login, Registro, Home, Detalle, Formulario, Recordatorios, Perfil
```

Flujo de datos: `Screen (Compose) → ViewModel (StateFlow) → Repository → Room / DataStore`.

##  Cómo ejecutar

**Requisitos:** Android Studio (versión reciente), JDK 17 (incluido en Android Studio), emulador o dispositivo con Android 8.0+ (API 26).

1. Clona el repositorio: `git clone <URL-del-repositorio>`
2. Abre la carpeta `PetCare` en Android Studio (**File ▸ Open**).
3. Espera a que termine **Gradle Sync** (descarga dependencias; requiere internet).
4. Selecciona un emulador o dispositivo y presiona **Run ▶**.
5. Para correr los tests unitarios: `./gradlew test` (o clic derecho en `ValidatorsTest` ▸ Run).

**Para probar las funciones nativas:**
- *Cámara:* en el emulador usa **Extended controls ▸ Camera** (cámara virtual) o un dispositivo físico.
- *Notificaciones:* acepta el permiso al agregar un cuidado y programa uno para dentro de 1–2 minutos. WorkManager puede retrasar unos minutos la entrega para ahorrar batería.

##  Notas de seguridad

- Las contraseñas no se guardan en texto plano (hash SHA-256 con el correo como sal). Para un producto real se recomienda un algoritmo de derivación de claves (bcrypt/Argon2) o un backend de autenticación.
- Las fotos se guardan en el almacenamiento interno de la app y se comparten con la cámara mediante `FileProvider`.
- Los permisos se solicitan en tiempo de ejecución con explicación previa y manejo del caso "denegado".

##  Próximos pasos (hacia la EFT)

- Sincronización en la nube / backend.
- Recordatorios repetitivos (por ejemplo, vacunas anuales).
- Ubicación para mostrar veterinarias dentro de la app.
- Pruebas de UI y más cobertura de tests.
