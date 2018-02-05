package es.um.nosql.s13e.decisiontree.utils;

import weka.classifiers.trees.J48;
import weka.classifiers.trees.j48.ClassifierTree;

public class OpenJ48 extends J48
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7599391179295602656L;

	public ClassifierTree get_m_root(){
		return this.m_root;
	}
}
