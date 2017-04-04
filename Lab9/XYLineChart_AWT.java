import java.awt.Color; 
import java.awt.BasicStroke; 
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.data.xy.XYDataset; 
import org.jfree.data.xy.XYSeries; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 
import org.jfree.chart.plot.XYPlot; 
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.plot.PlotOrientation; 
import org.jfree.data.xy.XYSeriesCollection; 
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

/**
 * The class uses JFreeChart library to create a xy-line chart to visualize the result
 * @author Thanh
 *
 */
public class XYLineChart_AWT extends ApplicationFrame 
{
   /**
    * overloaded constructor
    * @param title for the application
    * @param title for the chart
    */
   public XYLineChart_AWT( String applicationTitle, String chartTitle )
   {
      super(applicationTitle);
      JFreeChart xylineChart = ChartFactory.createXYLineChart(
         chartTitle ,
         "Recursion Limit" ,
         "Time to complete sort (second)" ,
         createDataset() ,
         PlotOrientation.VERTICAL ,
         true , true , false);
         
      ChartPanel chartPanel = new ChartPanel( xylineChart );
      chartPanel.setPreferredSize( new java.awt.Dimension( 1260 , 870 ) );
      final XYPlot plot = xylineChart.getXYPlot( );
      XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
      renderer.setSeriesPaint( 0 , Color.RED );
      renderer.setSeriesPaint( 1 , Color.GREEN );
      renderer.setSeriesPaint( 2 , Color.YELLOW );
      renderer.setSeriesPaint( 3 , Color.BLACK );
      renderer.setSeriesPaint( 4 , Color.MAGENTA );
      renderer.setSeriesPaint( 5 , Color.BLUE );
      renderer.setSeriesStroke( 0 , new BasicStroke( 3.0f ) );
      renderer.setSeriesStroke( 1 , new BasicStroke( 3.0f ) );
      renderer.setSeriesStroke( 2 , new BasicStroke( 3.0f ) );
      renderer.setSeriesStroke( 3 , new BasicStroke( 3.0f ) );
      renderer.setSeriesStroke( 4 , new BasicStroke( 3.0f ) );
      renderer.setSeriesStroke( 5 , new BasicStroke( 3.0f ) );
      plot.setRenderer( renderer ); 
      setContentPane( chartPanel ); 
   }
   
   /**
    * class to import the data into the chart
    * @return XYDataset
    */
   private XYDataset createDataset( )
   {      
      final XYSeries[] series = new XYSeries[Foothill.ARRAY_SIZES.length];
      final XYSeriesCollection dataset = new XYSeriesCollection( );  
      
      for(int i = 0; i < series.length; i++)
      {
         series[i] = new XYSeries(Foothill.ARRAY_SIZES[i]);
         
         for(int j = 0; j < Foothill.RECURS_LIMITS.length; j++)
         {
            series[i].add(Foothill.RECURS_LIMITS[j], Foothill.results[j * Foothill.ARRAY_SIZES.length + i]);
         }
         
         dataset.addSeries(series[i]);
      }     
      
      return dataset;
   }
}