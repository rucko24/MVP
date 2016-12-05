window.com_Core_vaadin_arduino_grafico_GraficoHighCharts = function() {
	
	this.onStateChange = function() {
		var chart = new Highchar.Chart
		(this.getState().data);
	}
}