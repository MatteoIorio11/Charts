package model;


import java.util.LinkedList;
import java.util.List;

import org.apache.commons.math.analysis.interpolation.*;
import org.apache.commons.math.analysis.polynomials.PolynomialSplineFunction;

public class LinearInterpolation {
	
	public static final int ACCURACY = 5;


	public static List<Double>interpolation(final double[] xs, final double[]ys, int[] points) {
		LinearInterpolator  li = new LinearInterpolator();
		PolynomialSplineFunction psf = li.interpolate(xs, ys);
		List<Double> out = new LinkedList<>();
		for(int start = 0; start < ACCURACY; start++) {
			try {
				out.add(psf.value(start));
			} catch (Exception e) {
				System.out.println("ERROR : " + e.getMessage());
				return out;
			}
		}
		return out;
	}
}
