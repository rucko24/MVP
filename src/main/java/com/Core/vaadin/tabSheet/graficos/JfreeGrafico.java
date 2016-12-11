package com.Core.vaadin.tabSheet.graficos;

import java.awt.Color;
import java.awt.GradientPaint;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYAreaRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.statistics.Regression;
import org.jfree.data.xy.DefaultTableXYDataset;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYSeries;
import org.vaadin.addon.JFreeChartWrapper;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

public class JfreeGrafico extends VerticalLayout {
	
	/***
	 * FIXME repinta la class principalCore
	 */
	public JfreeGrafico() {
		
		
		Component j = createBasicDemo();
		
		addComponent(j);

		setComponentAlignment(j, Alignment.BOTTOM_CENTER);
	//	addComponent(getLevelChart());

	//	addComponent(regressionChart());
		
	}
	
	public static JFreeChartWrapper createBasicDemo() {
		
        JFreeChart createchart = createchart(createDataset());
        JFreeChartWrapper jWrapper = new JFreeChartWrapper(createchart) {
        	@Override
        	public void attach() {
        		super.attach();
        		setResource("s", getSource());
        	}
        };
        
        return jWrapper;
        
    }
	private static CategoryDataset createDataset() {

        // row keys...
        String y2009 = "2009";
        String y2008 = "2008";
        String y2007 = "2007";

        // column keys...
        String under5 = "< 5";
        String between5_9 = "5-9";
        String between10_14 = "10-14";
        String between15_19 = "15-19";
        String between20_24 = "20-24";

        // create the dataset...
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(21299656, y2009, under5);
        dataset.addValue(20609634, y2009, between5_9);
        dataset.addValue(19973564, y2009, between10_14);
        dataset.addValue(21537837, y2009, between15_19);
        dataset.addValue(21539559, y2009, between20_24);

        dataset.addValue(21005852, y2008, under5);
        dataset.addValue(20065249, y2008, between5_9);
        dataset.addValue(20054627, y2008, between10_14);
        dataset.addValue(21514358, y2008, between15_19);
        dataset.addValue(21058981, y2008, between20_24);

        dataset.addValue(20724125, y2007, under5);
        dataset.addValue(19849628, y2007, between5_9);
        dataset.addValue(20314309, y2007, between10_14);
        dataset.addValue(21473690, y2007, between15_19);
        dataset.addValue(21032396, y2007, between20_24);

        return dataset;

    }
	
	private static JFreeChart createchart(CategoryDataset dataset) {

        // create the chart...
        JFreeChart chart = ChartFactory.createBarChart("Bar Chart Demo 1", // chart
                // title
                "Age group", // domain axis label
                "Number of members", // range axis label
                dataset, // data
                PlotOrientation.VERTICAL, // orientation
                true, // include legend
                true, // tooltips?
                false // URLs?
                );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...

        // set the background color for the chart...
        chart.setBackgroundPaint(Color.white);

        // get a reference to the plot for further customisation...
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setDomainGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.white);

        // ******************************************************************
        // More than 150 demo applications are included with the JFreeChart
        // Developer Guide...for more information, see:
        //
        // > http://www.object-refinery.com/jfreechart/guide.html
        //
        // ******************************************************************

        // set the range axis to display integers only...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        // disable bar outlines...
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        // renderer.setDrawBarOutline(false);

        // set up gradient paints for series...
        GradientPaint gp0 = new GradientPaint(0.0f, 0.0f, Color.blue, 0.0f,
                0.0f, new Color(0, 0, 64));
        GradientPaint gp1 = new GradientPaint(0.0f, 0.0f, Color.green, 0.0f,
                0.0f, new Color(0, 64, 0));
        GradientPaint gp2 = new GradientPaint(0.0f, 0.0f, Color.red, 0.0f,
                0.0f, new Color(64, 0, 0));
        renderer.setSeriesPaint(0, gp0);
        renderer.setSeriesPaint(1, gp1);
        renderer.setSeriesPaint(2, gp2);

        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions
                .createUpRotationLabelPositions(Math.PI / 6.0));
        // OPTIONAL CUSTOMISATION COMPLETED.

        return chart;

    }

	 private static Component getLevelChart() {

	        DefaultTableXYDataset ds = new DefaultTableXYDataset();
	        NumberAxis y = new NumberAxis("Sales in thousands of $");

	        XYSeries series;
	        Calendar cal = Calendar.getInstance();
	        cal.set(Calendar.YEAR, 2006);
	        long y2007 = cal.getTimeInMillis();
	        cal.set(Calendar.YEAR, 2007);
	        long y2008 = cal.getTimeInMillis();
	        cal.set(Calendar.YEAR, 2008);
	        long y2009 = cal.getTimeInMillis();

	        series = new XYSeries("Asia", false, false);
	        series.add(y2007, 130942);
	        series.add(y2008, 78730);
	        series.add(y2009, 86895);
	        ds.addSeries(series);

	        series = new XYSeries("Europe", false, false);
	        series.add(y2007, 207740);
	        series.add(y2008, 152144);
	        series.add(y2009, 130942);
	        ds.addSeries(series);

	        series = new XYSeries("USA", false, false);
	        series.add(y2007, 217047);
	        series.add(y2008, 129870);
	        series.add(y2009, 174850);
	        ds.addSeries(series);

	        // Paint p = new Color(0, 0, 0, Color.OPAQUE);
	        // r.setSeriesPaint(0, p);
	        // BasicStroke s = new BasicStroke(2);
	        // r.setSeriesStroke(0, s);

	        DateAxis x = new DateAxis("Year");
	        x.setDateFormatOverride(new SimpleDateFormat("yyyy"));
	        x.setTickUnit(new DateTickUnit(DateTickUnitType.YEAR, 1));

	        XYPlot plot2 = new XYPlot(ds, x, y, new XYAreaRenderer(
	                XYAreaRenderer.AREA_AND_SHAPES));
	        plot2.setForegroundAlpha(0.5f);

	        JFreeChart c = new JFreeChart(plot2);

	        JFreeChartWrapper jWrapper = new JFreeChartWrapper(c) {
	        	@Override
	        	public void attach() {
	        		super.attach();
	        		setResource("s", getSource());
	        	}
	        };
	        
	        return jWrapper;
	    }
	 
	 private static Component regressionChart() {

	        DefaultTableXYDataset ds = new DefaultTableXYDataset();

	        XYSeries series;

	        series = new XYSeries("Measured difference", false, false);
	        series.add(1, 1);
	        series.add(2, 4);
	        series.add(3, 6);
	        series.add(4, 9);
	        series.add(5, 9);
	        series.add(6, 11);
	        ds.addSeries(series);

	        JFreeChart scatterPlot = ChartFactory.createScatterPlot("Regression",
	                "cm", "Measuring checkpoint", ds, PlotOrientation.HORIZONTAL,
	                true, false, false);

	        XYPlot plot = (XYPlot) scatterPlot.getPlot();

	        double[] regression = Regression.getOLSRegression(ds, 0);

	        // regression line points

	        double v1 = regression[0] + regression[1] * 1;
	        double v2 = regression[0] + regression[1] * 6;

	        DefaultXYDataset ds2 = new DefaultXYDataset();
	        ds2.addSeries("regline", new double[][] { new double[] { 1, 6 },
	                new double[] { v1, v2 } });
	        plot.setDataset(1, ds2);
	        plot.setRenderer(1, new XYLineAndShapeRenderer(true, false));

	        JFreeChart c = new JFreeChart(plot);
	       
	        JFreeChartWrapper jWrapper = new JFreeChartWrapper(c) {
	        	@Override
	        	public void attach() {
	        		super.attach();
	        		setResource("s", getSource());
	        	}
	        };
	        
	        return jWrapper;
	    }
	
	/*private JFreeChart grafica;
	private XYSeriesCollection datos = new XYSeriesCollection();
	
	//constantes 
	public final static int LINEA = 1;
	public final static int POLAR = 2;
	public final static int DISPERSION = 3;
	public final static int AREA = 4;
	public final static int LOGARITMICA = 5;
	public final static int SERIETIEMPO = 6;
	public final static int PASO = 7;
	public final static int PASOAREA = 8;
	
	private double x[];
	private double y[];
	
	private String titulo;
	public static String tX;
	public static String tY;
	
	public JfreeGrafico(int tipo, String titulo) {
		
		setSizeFull();
		this.titulo = titulo;
		tipoGrafica(tipo);
		
		Component j = jWrapper();
		
		addComponent(j);
		setComponentAlignment(j, Alignment.BOTTOM_CENTER);
	}
	
	public void tipoGrafica(int tipo) {
		switch(tipo) {
		case LINEA:
			grafica = ChartFactory.createXYLineChart(titulo, tX, tY , datos, PlotOrientation.VERTICAL, true,true,true);
			break;
		case POLAR:
			grafica = ChartFactory.createPolarChart(titulo, datos, true,true,true);
			break;
		case DISPERSION:
			grafica = ChartFactory.createScatterPlot(titulo, tX, tY, datos, PlotOrientation.VERTICAL, true, true, true);
			break;
		case AREA:
			grafica = ChartFactory.createXYAreaChart(titulo, tX, tY, datos, PlotOrientation.VERTICAL, true, true, true); 
			break;
			
		case LOGARITMICA:
			grafica = ChartFactory.createXYLineChart(titulo, tX, tY, datos, PlotOrientation.VERTICAL, true ,true, true);
			//una variable para recojer los eje de la grafica
			XYPlot eje = grafica.getXYPlot();
			//creando la escala logaritmica
			NumberAxis rango = new LogarithmicAxis(tY);
			//asignar al eje en el rango, cambiando la escala logaritmica
			eje.setRangeAxis(rango);
			//cambia las escalas de las Y
			break;
		case SERIETIEMPO:
			grafica = ChartFactory.createTimeSeriesChart(titulo, tX, tY, datos, true, true, true);
			break;
		case PASO: 
			grafica = ChartFactory.createXYStepAreaChart(titulo, tX, tY, datos, PlotOrientation.VERTICAL, true, true, true);
			break;
		case PASOAREA:
			grafica = ChartFactory.createXYAreaChart(titulo, tX, tY, datos, PlotOrientation.VERTICAL, true, true, true);
			break;
		}

		x = rango(1,360,0.5);
		//llenando los valores de y con respecto al los de x
		//donde pasamos el vector x, y regresara el SENO
		y = f(x);
		
		agregarGrafica("sin(x)", x, y);
		
	}
	
	public JFreeChartWrapper jWrapper() {
		
		JFreeChartWrapper wrapper = new JFreeChartWrapper(grafica) {
        	@Override
        	public void attach() {
        		super.attach();
        		setResource("src", getSource());
        	}
		};
		
		return wrapper;
	}
	
	public void agregarGrafica(String id, double x[], double y[]) {
		//pasando id de cada uno de las graficas
		XYSeries s = new XYSeries(id);
		//asignar valores que nos esten llegando
		int n = x.length; //numeros de registros que se van a asignar;
		//para ir asignando cada uno de los valores
		//asigano valores de x y y
		for( int f=0; f<n; f++) {
			s.add(x[f], y[f]);
		}
		//asignado series a los datos, para almacernarlos y sumando
		datos.addSeries(s);
	}
	
	
	//metodo para tener los datos y add a la chart
	//2 vectores 
	public double[] rango(double xInicial, double xFinal, double d) {
		
		//genereando una series de datos
		//contar numero de datos
		int n = (int) ((xFinal - xInicial)/d);
		//arreglo para regresar los datos
		double r[] = new double[n];
		//for para generar todos los datos
		
		for( int f=0; f<n; f++) {
			//d = incrementos, hasta llegar al ultimo valor
			r[f] = xInicial + d * f;
		}
		
		return r;
	}
	
	public double[] f(double[] x) {
		//revibiendo vertor con todas las X
		//y aqui generamos todas las Y
		int n = x.length;
		//
		double[] y = new double[n];
		//
		for( int f=0; f<n; f++) {
			//EL seno(PERO EN radianes)necesitamos pasar grados 
			//dividirlo entre 180 para lograr la 
			//conversion,,,la funsion seno recibe radianes
			double radianes = x[f] * Math.PI / 180;
			y[f] = Math.sin(radianes);
		}
		
		return y;
	}*/
}
