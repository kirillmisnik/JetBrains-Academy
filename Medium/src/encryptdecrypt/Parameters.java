package encryptdecrypt;

public class Parameters {

    public String alg = "shift";
    public String mode = "enc";
    public String data = "";
    public String in = "";
    public String out = "";
    public int key = 0;

    Parameters(String[] args) {
        for (int i = 0; i < args.length - 1; i++) {
            switch (args[i]) {
                case "-mode":
                    this.mode = args[i + 1];
                    break;
                case "-data":
                    this.data = args[i + 1];
                    break;
                case "-key":
                    this.key = Integer.parseInt(args[i + 1]);
                    break;
                case "-in":
                    this.in = args[i + 1];
                    break;
                case "-out":
                    this.out = args[i + 1];
                    break;
                case "-alg":
                    this.alg = args[i + 1];
                    break;
            }
        }
    }
}
