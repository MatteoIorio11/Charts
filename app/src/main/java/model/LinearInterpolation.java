package model;


import java.util.LinkedList;
import java.util.List;

import org.apache.commons.math.ArgumentOutsideDomainException;
import org.apache.commons.math.analysis.interpolation.LinearInterpolator;
import org.apache.commons.math.analysis.polynomials.PolynomialSplineFunction;

public class LinearInterpolation {
	
	public static final int ACCURACY = 100;


	public static List<Double>interpolation(final double[] xs, final double[]ys, int[] points) {
		LinearInterpolator li = new LinearInterpolator();
		PolynomialSplineFunction psf = li.interpolate(xs, ys);
		List<Double> out = new LinkedList<>();
		for(int point : points) {
			try {
				out.add(psf.value(point));
				
			} catch (ArgumentOutsideDomainException e) {
				e.printStackTrace();
			}
		}
		return out;
	}
}
