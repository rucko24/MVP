package org.vaadin.highcharts;

import com.vaadin.annotations.JavaScript;

import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

@JavaScript({"jquery-3.1.1.min.js", "highcharts.js", "highcharts-connector.js"})
public class HighChart extends AbstractHighChart {
    private static final long serialVersionUID = -7326315426217377753L;
    private Queue<Integer> queue = new LinkedBlockingQueue<>(Arrays.asList(0,0,0,0,0,0,0,0,0,0));

    public void addValue(Integer value) {
        queue.add(value);
        queue.remove();
        super.setHcjs(getScript());

    }

    private String getScript(){
        final String arrayValues = Arrays.toString(queue.toArray());
        System.err.println(arrayValues);
       return "var options = {\n" +
                "\ttitle: {\n" +
                "\t\ttext: 'Luminosidad'\n" +
                "\t},\n" +
                "\tseries: [\n" +
                "\t\t{\n" +
                "\t\t\tname: 's1',\n" +
                "\t\t\tdata: "+ arrayValues+"\n" +
                "\t\t}\n" +
                "\t],\n" +
                "\tchart: {\n" +
                "\t\tevents: {\n" +
                "\t\t\tclick: function (event) {\n" +
                "\t\t\t\tconnector.onClick(event.xAxis[0].value, event.yAxis[0].value);\n" +
                "\t\t\t}\n" +
                "\t\t}\n" +
                "\t}\n" +
                "};";
    }
}