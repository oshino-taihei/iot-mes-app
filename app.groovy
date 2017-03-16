import com.oracle.iot.client.*
import com.oracle.iot.client.device.*
import com.oracle.iot.client.message.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Controller
class IoTMesApp {
    private static final Logger logger = LoggerFactory.getLogger(IoTMesApp.class);
    private static final String URN_DEVICE = "urn:Accenture:JPTechForMES";
    private static final String PROVISIONING_FILE_PATH = "config/JPTechDummyForMES-provisioning-file.conf";
    private static final String PROVISIONING_FILE_PASS = "PYBSq85bwlIlh2vF";

    @RequestMapping("/")
    @ResponseBody
    String home() {
      def binding = ['dummy':'dummy']
      render('home', binding)
    }
    @RequestMapping("/submit")
    String submit(@RequestParam Map<String, String> params) {
      logger.info("make a message. params=${params}")
      DirectlyConnectedDevice device = new DirectlyConnectedDevice(PROVISIONING_FILE_PATH, PROVISIONING_FILE_PASS);
      if (!device.isActivated()) {
        device.activate(URN_DEVICE);
      }
      def messageBuilder = new DataMessage.Builder().format("${URN_DEVICE}:attributes").source(device.getEndpointId())
      params.each { key, value -> messageBuilder.dataItem(key, value) }
      DataMessage message = messageBuilder.build()
      device.send(message)
      device.close()
      logger.info("finished sending the message. params=${params}")
      "redirect:/"
    }

    private String render(String templateName, binding) {
      def f = new File("view/${templateName}.template")
      def engine = new groovy.text.SimpleTemplateEngine()
      def template = engine.createTemplate(f).make(binding)
      template.toString()
    }
}
