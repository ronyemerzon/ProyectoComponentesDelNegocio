import sys
from PyQt5.QtWidgets import QApplication
from PyQt5.QtGui import QTextDocument
from PyQt5.QtPrintSupport import QPrinter

def build_pdf():
    # Creamos la aplicación Qt necesaria para renderizar
    app = QApplication(sys.argv)
    
    html_content = """
    <html>
    <head>
    <style>
        body {
            font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;
            color: #333333;
            line-height: 1.5;
            margin: 40px;
        }
        h1 {
            color: #1a365d;
            font-size: 24pt;
            text-align: center;
            margin-top: 50px;
            margin-bottom: 10px;
        }
        h2 {
            color: #2b6cb0;
            font-size: 16pt;
            border-bottom: 2px solid #e2e8f0;
            padding-bottom: 5px;
            margin-top: 30px;
        }
        h3 {
            color: #2d3748;
            font-size: 12pt;
            margin-top: 20px;
        }
        p {
            font-size: 10pt;
            text-align: justify;
        }
        .subtitle {
            text-align: center;
            font-size: 14pt;
            color: #4a5568;
            margin-bottom: 50px;
        }
        .portada-info {
            margin-top: 150px;
            text-align: center;
            font-size: 11pt;
        }
        .portada-info table {
            margin: 0 auto;
            border-collapse: collapse;
        }
        .portada-info td {
            padding: 8px;
            text-align: left;
        }
        .page-break {
            page-break-before: always;
        }
        table.data-table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 15px;
            margin-bottom: 15px;
        }
        table.data-table th {
            background-color: #2b6cb0;
            color: white;
            padding: 8px;
            font-size: 9pt;
            text-align: left;
            border: 1px solid #cbd5e0;
        }
        table.data-table td {
            padding: 8px;
            font-size: 9pt;
            border: 1px solid #cbd5e0;
        }
        table.data-table tr:nth-child(even) {
            background-color: #f7fafc;
        }
        .code-block {
            background-color: #f7fafc;
            border-left: 4px solid #4a5568;
            padding: 10px;
            font-family: 'Courier New', Courier, monospace;
            font-size: 8.5pt;
            margin-top: 10px;
            margin-bottom: 10px;
            white-space: pre-wrap;
        }
        .screenshot-placeholder {
            border: 2px dashed #a0aec0;
            background-color: #edf2f7;
            text-align: center;
            padding: 40px;
            color: #718096;
            font-size: 10pt;
            font-weight: bold;
            margin-top: 15px;
            margin-bottom: 15px;
            border-radius: 5px;
        }
        .badge {
            background-color: #e2e8f0;
            color: #4a5568;
            padding: 2px 6px;
            border-radius: 4px;
            font-size: 8pt;
            font-family: monospace;
        }
    </style>
    </head>
    <body>

        <!-- PÁGINA 1: PORTADA -->
        <div style="text-align: center; margin-top: 80px;">
            <div style="font-size: 16pt; font-weight: bold; color: #1a365d; letter-spacing: 2px;">INSTITUTO DE EDUCACIÓN SUPERIOR IDAT</div>
            <div style="font-size: 11pt; color: #718096; margin-top: 5px;">TECNOLOGÍA DE LA INFORMACIÓN</div>
            
            <h1>INFORME TÉCNICO DE PROYECTO</h1>
            <div class="subtitle">API RESTful para la Gestión de Solicitudes de Soporte Técnico</div>
            
            <div style="margin-top: 40px; font-size: 11pt; font-weight: bold; color: #2d3748;">
                CURSO: Desarrollo de los Componentes del Negocio
            </div>
            <div style="font-size: 10pt; color: #4a5568;">4to Ciclo - Sección Backend Java</div>

            <div class="portada-info">
                <table style="width: 80%; border: none;">
                    <tr>
                        <td style="font-weight: bold; width: 30%;">Docente:</td>
                        <td>Profesor del Curso de Componentes de Negocio</td>
                    </tr>
                    <tr>
                        <td style="font-weight: bold;">Autores / Equipo:</td>
                        <td>
                            • Integrante 1 (Líder / Backend Developer)<br/>
                            • Integrante 2 (Backend Developer)<br/>
                            • Integrante 3 (QA / Backend Developer)
                        </td>
                    </tr>
                    <tr>
                        <td style="font-weight: bold;">Enlace GitHub:</td>
                        <td><span style="color: #2b6cb0; text-decoration: underline;">https://github.com/usuario/ProyectoJavaIDAT</span></td>
                    </tr>
                    <tr>
                        <td style="font-weight: bold;">Fecha:</td>
                        <td>Julio, 2026</td>
                    </tr>
                </table>
            </div>
            
            <div style="margin-top: 100px; font-size: 9pt; color: #a0aec0;">LIMA - PERÚ</div>
        </div>

        <div class="page-break"></div>

        <!-- PÁGINA 2: DESCRIPCIÓN Y ARQUITECTURA -->
        <h2>1. Descripción General del Proyecto</h2>
        <p>
            El presente proyecto backend consiste en una API RESTful desarrollada con <strong>Java 21</strong> y <strong>Spring Boot 4.1.0</strong> para el registro, consulta, actualización y eliminación (CRUD) de solicitudes de soporte técnico. 
            Su diseño nace de la necesidad de una empresa de servicios tecnológicos que requería automatizar la atención de solicitudes de sus clientes, las cuales anteriormente se gestionaban mediante canales informales y desordenados (papel y correos electrónicos), ocasionando demoras y pérdida de información.
        </p>
        <p>
            Para cumplir con la rúbrica académica, la aplicación gestiona la persistencia de datos directamente en <strong>memoria del servidor</strong>, implementando colecciones concurrentes que simulan bases de datos relacionales sin necesidad de dependencias de bases de datos físicas, asegurando un arranque instantáneo y seguro en entornos concurrentes.
        </p>

        <h2>2. Estructura y Arquitectura en Capas</h2>
        <p>
            El sistema se ha estructurado siguiendo las mejores prácticas de la industria con el patrón de <strong>arquitectura por capas</strong>. Esto permite un acoplamiento débil entre componentes y facilita la mantenibilidad del código:
        </p>
        
        <table class="data-table">
            <thead>
                <tr>
                    <th style="width: 25%;">Capa (Paquete)</th>
                    <th style="width: 35%;">Responsabilidad</th>
                    <th style="width: 40%;">Clases Clave</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td><strong>controller</strong></td>
                    <td>Exponer los endpoints de la API, capturar peticiones HTTP y retornar respuestas JSON.</td>
                    <td>ClienteController, TecnicoController, SolicitudController</td>
                </tr>
                <tr>
                    <td><strong>service</strong></td>
                    <td>Contener la lógica del negocio e integrar las reglas y validaciones relacionales del sistema.</td>
                    <td>ClienteService, TecnicoService, SolicitudService</td>
                </tr>
                <tr>
                    <td><strong>repository</strong></td>
                    <td>Gestionar el acceso y manipulación de datos almacenados temporalmente en memoria (RAM).</td>
                    <td>ClienteRepository, TecnicoRepository, SolicitudRepository</td>
                </tr>
                <tr>
                    <td><strong>model</strong></td>
                    <td>Definir las entidades del dominio de la aplicación (objetos de datos reales).</td>
                    <td>Cliente, Tecnico, Solicitud, EstadoSolicitud, PrioridadSolicitud</td>
                </tr>
                <tr>
                    <td><strong>dto</strong></td>
                    <td>Objetos de transferencia para desacoplar el envío de datos externos del modelo del dominio.</td>
                    <td>SolicitudRequest</td>
                </tr>
                <tr>
                    <td><strong>exception</strong></td>
                    <td>Mapear y gestionar centralizadamente las excepciones de negocio y de validación de campos.</td>
                    <td>GlobalExceptionHandler, ResourceNotFoundException, ErrorResponse</td>
                </tr>
                <tr>
                    <td><strong>config</strong></td>
                    <td>Configuración global de la aplicación (e.g. habilitación de políticas de CORS).</td>
                    <td>CorsConfig</td>
                </tr>
            </tbody>
        </table>

        <div class="page-break"></div>

        <!-- PÁGINA 3: ROLES DEL EQUIPO Y DETALLES DE IMPLEMENTACIÓN -->
        <h2>3. Roles del Equipo de Desarrollo</h2>
        <p>
            Para emular un flujo de trabajo ágil e industrial, el equipo se dividió las responsabilidades técnicas del proyecto de la siguiente forma:
        </p>
        <ul>
            <li>
                <strong>Integrante 1 (Diseño de Modelo y Servicios)</strong>:<br/>
                Responsable de estructurar las clases del modelo de datos (<code>Cliente</code>, <code>Tecnico</code>, <code>Solicitud</code>) y sus relaciones. Diseñó e implementó las interfaces de servicio y la persistencia segura simulada en memoria mediante el uso de <code>ConcurrentHashMap</code> y <code>AtomicLong</code> en los repositorios.
            </li>
            <li style="margin-top: 8px;">
                <strong>Integrante 2 (Controladores REST y Validaciones)</strong>:<br/>
                Encargado del desarrollo de la capa de controladores REST (<code>ClienteController</code>, <code>TecnicoController</code> y <code>SolicitudController</code>). Integró y configuró la validación estricta de las solicitudes utilizando la especificación de <code>Jakarta Validation</code> (<code>@Valid</code>, <code>@NotBlank</code>, <code>@Email</code>).
            </li>
            <li style="margin-top: 8px;">
                <strong>Integrante 3 (Manejo de Errores y Documentación)</strong>:<br/>
                Implementó el interceptor global de excepciones <code>GlobalExceptionHandler</code> con la anotación <code>@RestControllerAdvice</code>. Adicionalmente, integró <code>Springdoc-OpenAPI/Swagger</code> para la autogeneración de la documentación de endpoints y lideró el plan de pruebas funcionales.
            </li>
        </ul>

        <h2>4. Explicación del Código Destacado</h2>
        
        <h3>Manejo de Persistencia In-Memory (Simulada)</h3>
        <p>
            Para garantizar que las operaciones CRUD se ejecuten correctamente sin colisiones de hilos de ejecución en el servidor web, el repositorio se implementó de la siguiente forma:
        </p>
        <div class="code-block">
@Repository
public class ClienteRepository {
    private final Map&lt;Long, Cliente&gt; clientes = new ConcurrentHashMap&lt;&gt;();
    private final AtomicLong idGenerator = new AtomicLong(0);

    public Cliente save(Cliente cliente) {
        if (cliente.getId() == null) {
            cliente.setId(idGenerator.incrementAndGet());
        }
        clientes.put(cliente.getId(), cliente);
        return cliente;
    }
    // findAll, findById, deleteById...
}
        </div>

        <div class="page-break"></div>

        <!-- PÁGINA 4: PRUEBAS FUNCIONALES CON POSTMAN (PARTE I) -->
        <h2>5. Informe de Pruebas Funcionales (Postman / Swagger)</h2>
        <p>
            A continuación se listan los casos de prueba ejecutados para garantizar el correcto funcionamiento de los endpoints CRUD, las validaciones de entrada y el manejador de excepciones.
        </p>

        <h3>Prueba 1: Listado General de Clientes (GET)</h3>
        <p>
            <strong>Endpoint:</strong> <span class="badge">GET http://localhost:8081/api/clientes</span><br/>
            <strong>Resultado esperado:</strong> Retorna la lista con los 3 clientes precargados por la semilla de datos con código <span class="badge">200 OK</span>.
        </p>
        <div class="screenshot-placeholder">
            [ PEGAR CAPTURA DE PANTALLA POSTMAN AQUÍ - GET /api/clientes ]
        </div>

        <h3>Prueba 2: Registro de Cliente Exitoso (POST)</h3>
        <p>
            <strong>Endpoint:</strong> <span class="badge">POST http://localhost:8081/api/clientes</span><br/>
            <strong>Payload de entrada:</strong> Envia nombre, email válido y teléfono.<br/>
            <strong>Resultado esperado:</strong> Cliente registrado con ID incremental 4 y código <span class="badge">201 Created</span>.
        </p>
        <div class="screenshot-placeholder">
            [ PEGAR CAPTURA DE PANTALLA POSTMAN AQUÍ - POST /api/clientes ]
        </div>

        <div class="page-break"></div>

        <!-- PÁGINA 5: PRUEBAS FUNCIONALES CON POSTMAN (PARTE II) -->
        <h3>Prueba 3: Intento de Registro con Email Inválido (Validación Fallida)</h3>
        <p>
            <strong>Endpoint:</strong> <span class="badge">POST http://localhost:8081/api/clientes</span><br/>
            <strong>Payload de entrada:</strong> Email sin formato arroba ni dominio (ej: "correo_invalido").<br/>
            <strong>Resultado esperado:</strong> Retorno código <span class="badge">400 Bad Request</span> con el JSON de error estructurado indicando que el correo no es válido.
        </p>
        <div class="screenshot-placeholder">
            [ PEGAR CAPTURA DE PANTALLA POSTMAN AQUÍ - ERROR 400 EMAIL INVÁLIDO ]
        </div>

        <h3>Prueba 4: Registro de Solicitud de Soporte (POST)</h3>
        <p>
            <strong>Endpoint:</strong> <span class="badge">POST http://localhost:8081/api/solicitudes</span><br/>
            <strong>Payload DTO enviado:</strong>
        </p>
        <div class="code-block">
{
    "descripcion": "El servidor DNS de la oficina no resuelve dominios externos.",
    "prioridad": "ALTA",
    "clienteId": 1,
    "tecnicoId": 1
}
        </div>
        <p>
            <strong>Resultado esperado:</strong> Creación de la solicitud en estado <code>PENDIENTE</code> con el cliente y técnico completamente vinculados. Código <span class="badge">201 Created</span>.
        </p>
        <div class="screenshot-placeholder">
            [ PEGAR CAPTURA DE PANTALLA POSTMAN AQUÍ - POST /api/solicitudes ]
        </div>

        <div class="page-break"></div>

        <!-- PÁGINA 6: PRUEBAS FUNCIONALES Y CONCLUSIONES -->
        <h3>Prueba 5: Intento de Crear Solicitud con Cliente Inexistente (Excepción de Negocio)</h3>
        <p>
            <strong>Endpoint:</strong> <span class="badge">POST http://localhost:8081/api/solicitudes</span><br/>
            <strong>Payload DTO enviado:</strong> <code>"clienteId": 999</code> (inexistente).<br/>
            <strong>Resultado esperado:</strong> Código de error <span class="badge">404 Not Found</span> con el mensaje: <em>"Cliente no encontrado con ID: 999"</em> devuelto por el interceptor global.
        </p>
        <div class="screenshot-placeholder">
            [ PEGAR CAPTURA DE PANTALLA POSTMAN AQUÍ - ERROR 404 CLIENTE INEXISTENTE ]
        </div>

        <h2>6. Conclusiones y Logros del Proyecto</h2>
        <ul>
            <li>
                <strong>Eficiencia en el Desarrollo</strong>: La arquitectura por capas implementada permitió al equipo avanzar de forma paralela en los controladores, lógica de negocio y repositorios sin bloqueos.
            </li>
            <li style="margin-top: 8px;">
                <strong>Seguridad y Robustez en Datos</strong>: El uso de <code>Jakarta Validation</code> a nivel de controlador garantiza que no ingresen solicitudes incompletas o corruptas al sistema.
            </li>
            <li style="margin-top: 8px;">
                <strong>Manejo Limpio de Errores</strong>: El interceptor global brinda una gran experiencia de desarrollo al consumir la API, ya que estandariza las respuestas de error facilitando la depuración en frontend.
            </li>
            <li style="margin-top: 8px;">
                <strong>Independencia Tecnológica</strong>: Gracias al desacoplamiento en capas, el almacenamiento in-memory puede ser reemplazado por bases de datos físicas (JPA/SQL) en minutos sin alterar los controladores o servicios existentes.
            </li>
        </ul>

    </body>
    </html>
    """
    
    # Cargamos el HTML en el documento de texto de Qt
    doc = QTextDocument()
    doc.setHtml(html_content)
    
    # Configuramos el Printer para exportar PDF
    printer = QPrinter()
    printer.setOutputFormat(QPrinter.PdfFormat)
    printer.setPageSize(QPrinter.A4)
    printer.setOutputFileName("Informe_Tecnico_ProyectoJavaIDAT.pdf")
    
    # Imprimimos/exportamos el documento a PDF
    doc.print_(printer)
    print("El PDF oficial ha sido generado: 'Informe_Tecnico_ProyectoJavaIDAT.pdf'")

if __name__ == "__main__":
    build_pdf()
