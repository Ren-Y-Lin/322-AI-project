package ubc.cosc322;
import java.io.IOException;

import org.deeplearning4j.nn.modelimport.keras.KerasModelImport;
import org.deeplearning4j.nn.modelimport.keras.exceptions.InvalidKerasConfigurationException;
import org.deeplearning4j.nn.modelimport.keras.exceptions.UnsupportedKerasConfigurationException;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.common.io.ClassPathResource;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;


public class ComponentTester {
	
	public static void main(String[] args) {
		
		
		try {
			String simpleMlp = new ClassPathResource("model2.h5").getFile().getPath();
			System.out.println(simpleMlp);
			MultiLayerNetwork model = KerasModelImport.importKerasSequentialModelAndWeights(simpleMlp);
			double[][] board = {{0,0,0,2,0,0,2,0,0,0},{0,0,0,0,0,0,0,0,0,0},{2,0,0,0,0,0,0,0,0,2},
					{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},
					{1,0,0,0,0,0,0,0,0,1},{0,0,0,0,0,0,0,0,0,0},{0,0,0,1,0,0,1,0,0,0}};
			int[] shape= {1,10,10,1}; 
			
			INDArray features = Nd4j.rand(shape);
			features.muli(29);
			System.out.println(features.toString());
			
			double prediction = model.output(features).getDouble(0);
			System.out.println(prediction);
			
			
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKerasConfigurationException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedKerasConfigurationException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
