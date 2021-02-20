package ubc.cosc322;


import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Arrays;

import org.deeplearning4j.nn.modelimport.keras.KerasModelImport;
import org.deeplearning4j.nn.modelimport.keras.exceptions.InvalidKerasConfigurationException;
import org.deeplearning4j.nn.modelimport.keras.exceptions.UnsupportedKerasConfigurationException;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.junit.jupiter.api.*;
import org.nd4j.common.io.ClassPathResource;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

class TotalTesting {
	static boolean r;
	
	@BeforeAll
	static void setup() {
		
		r = true;
		
	}
	
	

	@RepeatedTest(5)
	@DisplayName("Test that tests works")
	void test() {
		assertTrue(r);
	}
	
	
	@RepeatedTest(5)
	@DisplayName("Ensures Neural Network outputs correct values")
	void testNeuralEvaluator() {
		
		try {
			String simpleMlp = new ClassPathResource("model2.h5").getFile().getPath();
			System.out.println(simpleMlp);
			MultiLayerNetwork model = KerasModelImport.importKerasSequentialModelAndWeights(simpleMlp);
			double[][][][] board = {{{{0},{0},{0},{2},{0},{0},{2},{0},{0},{0}},{{0},{0},{0},{0},{0},{0},{0},{0},{0},{0}},{{2},{0},{0},{0},{0},{0},{0},{0},{0},{2}},
				{{0},{0},{0},{0},{0},{0},{0},{0},{0},{0}},{{0},{0},{0},{0},{0},{0},{0},{0},{0},{0}},{{0},{0},{0},{0},{0},{0},{0},{0},{0},{0}},{{0},{0},{0},{0},{0},{0},{0},{0},{0},{0}},
				{{1},{0},{0},{0},{0},{0},{0},{0},{0},{1}},{{0},{0},{0},{0},{0},{0},{0},{0},{0},{0}},{{0},{0},{0},{1},{0},{0},{1},{0},{0},{0}}}};
			//int[] shape= {1,10,10,1}; 
			
			INDArray features = Nd4j.createFromArray(board);
			System.out.println(Arrays.toString(features.shape()));
			//features.muli(29);
			System.out.println(features.toString());
			
			double prediction = model.output(features).getDouble(0);
			System.out.println(prediction);
			
			assertTrue(prediction>=0);
			assertTrue(prediction<=1);
			
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
