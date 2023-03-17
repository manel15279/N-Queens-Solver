package com.example.nqueens;

import java.util.ArrayList;

public class Node1 implements Comparable<Node1> {
    ArrayList<Integer> echiq;
    int r,h;

    public Node1(ArrayList<Integer> echiq, int r, int h) {
        this.echiq = echiq;
        this.r=r;
        this.h=h;
    }

    @Override
    public String toString() {
        return "echiq=" + echiq ;
    }
    
	// compareTo permet de comparer deux noeud selon leurs fonction f. elle nous permet de trier ouvert selon f.
	public int compareTo(Node1 n) {
        return Integer.compare(n.h,h);
    }
}
