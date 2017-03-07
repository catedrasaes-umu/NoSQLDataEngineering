package es.um.nosql.schemainference.decisiontree.utils;

import weka.classifiers.trees.J48;
import weka.classifiers.trees.j48.ClassifierTree;

public class OpenJ48 extends J48 {
	
	public ClassifierTree get_m_root(){
		return this.m_root;
	}

}
