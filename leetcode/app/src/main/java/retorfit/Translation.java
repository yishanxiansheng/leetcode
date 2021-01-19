package retorfit;

import java.util.Arrays;

/**
 * @author heshufan
 * @date 2021-01-19
 */
public class Translation {
    private String from;
    private String to;

    private TransResult[] trans_result;

    private static class TransResult {
        private String src;
        private String dst;

        @Override
        public String toString() {
            return "TransResult{" +
                    "src='" + src + '\'' +
                    ", dst='" + dst + '\'' +
                    '}';
        }
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public TransResult[] getTrans_result() {
        return trans_result;
    }

    public void setTrans_result(TransResult[] trans_result) {
        this.trans_result = trans_result;
    }

    @Override
    public String toString() {
        return "Translation{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", trans_result=" + Arrays.toString(trans_result) +
                '}';
    }
}
