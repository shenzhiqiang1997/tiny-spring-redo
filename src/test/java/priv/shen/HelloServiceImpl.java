package priv.shen;

/**
 * 测试用的主bean
 */
public class HelloServiceImpl implements HelloService{
    private OutputService outputService;
    private String text;

    public void hello(){
        outputService.output(text);
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setOutputService(OutputService outputService) {
        this.outputService = outputService;
    }
}
