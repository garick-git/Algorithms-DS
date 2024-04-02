package keyboard;

import static keyboard.Key.*;
import static keyboard.KeyLayout.COLEMAK;
import static keyboard.KeyLayout.DVORAK;
import static keyboard.KeyLayout.QWERTY;
import static keyboard.KeyLayout.ROTATION_13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import combinatorics.Permutation_Mendez;

/**
 * @author Mendez
 *
 */
public class AppleNumericMB110LLKeyboardMetricsImpl_Mendez implements KeyboardMetrics 
{
	private List<Key> vertexLabels;
	private int[][] adjacencyMatrix;
	private int[][] distanceMatrix;
	private Key homeKey;
	private boolean testIsQWERTY = false;
	private boolean testIsDVORAK = false;
	private boolean testIsROT13 = false;
	private boolean testIsCOLEMAK = false;

	
	//  Key layout --> Home key
	private static Map<KeyLayout, Key> keyLayoutToHomeKeyMap;
	
	// Key layout --> Key --> Neighbor keys of Key
	private static Map<KeyLayout, Map<Key, Set<Key>>> keyLayoutToKeyToNeighborMapMap;
	
	static
	{
		keyLayoutToHomeKeyMap = new HashMap<KeyLayout, Key>();
		keyLayoutToHomeKeyMap.put(QWERTY, J);
		keyLayoutToHomeKeyMap.put(DVORAK, H);
		keyLayoutToHomeKeyMap.put(COLEMAK, N);
		keyLayoutToHomeKeyMap.put(ROTATION_13, W);
		
		keyLayoutToKeyToNeighborMapMap = new HashMap<KeyLayout, Map<Key, Set<Key>>>();
//		Map<Key, Set<Key>> keyToNeighborMap_QWERTY = getKeyToNeighborMap_QWERTY();
//		Map<Key, Set<Key>> keyToNeighborMap_DVORAK = applyPermutationToMap(keyToNeighborMap_QWERTY, getQWERTYToDvorakPermutation());
//		Map<Key, Set<Key>> keyToNeighborMap_COLEMAK = applyPermutationToMap(keyToNeighborMap_QWERTY, getQWERTYToColemakPermutation());
//		Map<Key, Set<Key>> keyToNeighborMap_ROT_13 = applyPermutationToMap(keyToNeighborMap_QWERTY, getQWERTYToRotation13Permutation());
//		keyLayoutToKeyToNeighborMapMap.put(QWERTY, keyToNeighborMap_QWERTY);
//		keyLayoutToKeyToNeighborMapMap.put(DVORAK, keyToNeighborMap_DVORAK);
//		keyLayoutToKeyToNeighborMapMap.put(COLEMAK, keyToNeighborMap_COLEMAK);
//		keyLayoutToKeyToNeighborMapMap.put(ROTATION_13, keyToNeighborMap_ROT_13);
	}
	
	public AppleNumericMB110LLKeyboardMetricsImpl_Mendez(KeyLayout keyLayout)
	{
		this.homeKey = keyLayoutToHomeKeyMap.get(keyLayout);
		//create variable here to check which keyboard the test cases are testing for??
		if(keyLayout == QWERTY)
		{
			testIsQWERTY = true;

		}
		
		if(keyLayout == ROTATION_13)
		{
			testIsROT13 = true;

		}
		
		if(keyLayout == COLEMAK)
		{
			testIsCOLEMAK = true;

		}
		
		if(keyLayout == DVORAK)
		{
			testIsDVORAK = true;

		}
//		Map<Key, Set<Key>> keyToNeighborsMap = keyLayoutToKeyToNeighborMapMap.get(keyLayout);
//		init(keyToNeighborsMap, new ArrayList<Key>(keyToNeighborsMap.keySet()));
	}
	
	public void init(Map<Key, Set<Key>> physicalKeyToNeighborsMap, List<Key> vertexLabels)
	{
		this.vertexLabels = vertexLabels;
		this.adjacencyMatrix = getAdjacencyMatrix(physicalKeyToNeighborsMap, vertexLabels);
		this.distanceMatrix = getDistanceMatrix(adjacencyMatrix);
	}
	
	private static int[][] getAdjacencyMatrix(Map<Key, Set<Key>> physicalKeyToNeighborsMap, List<Key> vertexLabels)
	{
		assert physicalKeyToNeighborsMap.keySet().equals(new HashSet<Key>(vertexLabels)) : "vertexLabels inconsistent with physicalKeyToNeighborsMap! : vertexLabels = " + vertexLabels + " physicalKeyToNeighborsMap.keySet() = " + physicalKeyToNeighborsMap.keySet();
		final int SIZE = physicalKeyToNeighborsMap.keySet().size();
		int[][] adjacencyMatrix = new int[SIZE][SIZE];
		
		//build adjacencyMatrix here...
		
		return adjacencyMatrix;
	}
	
//	//Matrix multiplication
//	private static int[][] multiply(int[][] A, int[][] B)
//	{
//		int rowCount_A = A.length;
//		assert rowCount_A > 0 : "rowCount_A = 0!";
//		int columnCount_A = A[0].length;
//		int rowCount_B = B.length;
//		assert rowCount_B > 0 : "rowCount_B = 0!";
//		int columnCount_B = B[0].length;
//		assert columnCount_A == rowCount_B : "columnCount_A = " + columnCount_A + " <> " + rowCount_B + " = rowCount_B!";
//		
//		int[][] C = new int[rowCount_A][columnCount_B];
//        for (int i = 0; i < rowCount_A; i++)
//            for (int j = 0; j < columnCount_B; j++)
//                for (int k = 0; k < columnCount_A; k++)
//                    C[i][j] += A[i][k] * B[k][j];
//        
//        return C;
//	}
	
	private static int[][] getDistanceMatrix(int[][] adjacencyMatrix)
	{
		int vertexCount = adjacencyMatrix.length;
		assert vertexCount > 0 : "rowCount = 0!";
		
		int[][] distanceMatrix = new int[vertexCount][vertexCount];
		
		//Figure out distanceMatrix here...
		
		return distanceMatrix;
	}
	
	/* (non-Javadoc)
	 * @see keyboard.KeyboardMeasurements#getDistance(keyboard.PhysicalKey, keyboard.PhysicalKey)
	 */
	
	@Override
	public double getDistance(Key key1, Key key2) 
	{
		//int index1 = getIndex(vertexLabels, key1);
		//int index2 = getIndex(vertexLabels, key2);
		//return distanceMatrix[index1][index2];
		int distance = 0;
		if(testIsQWERTY == true)
		{
			distance = distanceBetweenTwoKeys_QWERTY(key1, key2);
		}
		
		if(testIsDVORAK == true)
		{
			distance = distanceBetweenTwoKeys_DVORAK(key1, key2);
		}
		
		if(testIsCOLEMAK == true)
		{
			distance = distanceBetweenTwoKeys_COLEMAK(key1, key2);
		}
		
		if(testIsROT13)
		{
			distance = distanceBetweenTwoKeys_ROT13(key1, key2);
		}
		
		return distance;
	}

//	private static <E> int getIndex(List<E> list, E element)
//	{
//		boolean foundIndex = false;
//		int i = 0;
//		while(!foundIndex && i < list.size())
//		{
//			foundIndex = (list.get(i) == element);
//			if(!foundIndex) i++;
//		}
//		int rv = -1;
//		if(foundIndex) rv = i;
//		return rv;
//	}

	@Override
	public double getDistance(String str) 
	{
		double distance = 0;
		Key currentKey = homeKey;
		boolean firstCharIsHomeKey = false;
		
		//Calculate distance here	
		distance = 0.0;
		
		if(testIsQWERTY)
		{
			Key[] keysArrayQWERTY = getStringToKeyArray_QWERTY(str);
			currentKey = J;
			// if the first character is J, starting distance is zero
			// else, starting distance is J to string char at(0)
			if(str.charAt(0) == 'j' || str.charAt(0) == 'J')
			{
				firstCharIsHomeKey = true;
			}
			
			for(int i = 0, k = 1; i < keysArrayQWERTY.length; i++, k++)
			{
				if(k == keysArrayQWERTY.length)
				{
					break;
				}
				else
				{
					distance = distance + (distanceBetweenTwoKeys_QWERTY(keysArrayQWERTY[i], keysArrayQWERTY[k]));
					//System.out.println("Distance between " + keysArrayQWERTY[i] + " and " + keysArrayQWERTY[k] + " is = " + distanceBetweenTwoKeys_QWERTY(keysArrayQWERTY[i], keysArrayQWERTY[k]));
				}			
			}
			
			if(firstCharIsHomeKey == false)
			{
				distance = distance + distanceBetweenTwoKeys_QWERTY(currentKey, keysArrayQWERTY[0]);
			}
		}
		
		if(testIsDVORAK)
		{
			Key[] keysArrayDVORAK = getStringToKeyArray_DVORAK(str);
			currentKey = H;
			// if the first character is J, starting distance is zero
			// else, starting distance is J to string char at(0)
			if(str.charAt(0) == 'h' || str.charAt(0) == 'H')
			{
				firstCharIsHomeKey = true;
			}
			
			for(int i = 0, k = 1; i < keysArrayDVORAK.length; i++, k++)
			{
				if(k == keysArrayDVORAK.length)
				{
					break;
				}
				else
				{
					distance = distance + (distanceBetweenTwoKeys_DVORAK(keysArrayDVORAK[i], keysArrayDVORAK[k]));
					//System.out.println("Distance between " + keysArrayDVORAK[i] + " and " + keysArrayDVORAK[k] + " is = " + distanceBetweenTwoKeys_QWERTY(keysArrayDVORAK[i], keysArrayDVORAK[k]));
				}			
			}
			
			if(firstCharIsHomeKey == false)
			{
				distance = distance + distanceBetweenTwoKeys_DVORAK(currentKey, keysArrayDVORAK[0]);
			}
		}
		
		if(testIsROT13)
		{
			Key[] keysArrayROT13 = getStringToKeyArray_ROT13(str);
			//System.out.println("Array of keys is = " + keysArrayROT13[1]);
			currentKey = W;
			if(str.charAt(0) == 'w' || str.charAt(0) == 'W')
			{
				firstCharIsHomeKey = true;
			}
			
			for(int i = 0, k = 1; i < keysArrayROT13.length; i++, k++)
			{
				if(k == keysArrayROT13.length)
				{
					break;
				}
				else
				{
					distance = distance + (distanceBetweenTwoKeys_ROT13(keysArrayROT13[i], keysArrayROT13[k]));
					//System.out.println(distance);
					//System.out.println("Distance between " + keysArrayROT13[i] + " and " + keysArrayROT13[k] + " is = " + distanceBetweenTwoKeys_ROT13(keysArrayROT13[i], keysArrayROT13[k]));
				}			
			}
			
			if(firstCharIsHomeKey == false)
			{
				distance = distance + distanceBetweenTwoKeys_ROT13(currentKey, keysArrayROT13[0]);
			}
		}
		
		if(testIsCOLEMAK)
		{
			Key[] keysArrayCOLEMAK = getStringToKeyArray_COLEMAK(str);
			currentKey = N;
			// if the first character is J, starting distance is zero
			// else, starting distance is J to string char at(0)
			if(str.charAt(0) == 'n' || str.charAt(0) == 'N')
			{
				firstCharIsHomeKey = true;
			}
			
			for(int i = 0, k = 1; i < keysArrayCOLEMAK.length; i++, k++)
			{
				if(k == keysArrayCOLEMAK.length)
				{
					break;
				}
				else
				{
					distance = distance + (distanceBetweenTwoKeys_COLEMAK(keysArrayCOLEMAK[i], keysArrayCOLEMAK[k]));
					//System.out.println("Distance between " + keysArrayCOLEMAK[i] + " and " + keysArrayCOLEMAK[k] + " is = " + distanceBetweenTwoKeys_COLEMAK(keysArrayCOLEMAK[i], keysArrayCOLEMAK[k]));
				}			
			}
			
			if(firstCharIsHomeKey == false)
			{
				distance = distance + distanceBetweenTwoKeys_COLEMAK(currentKey, keysArrayCOLEMAK[0]);
			}
		}
		
		return distance;
	}
	
	private Key getClosestKey(Set<Key> keySet, Key key)
	{
		double minDistance = 0.0;
		List<Key> keyList = new ArrayList<Key>(keySet);
		Key minDistanceKey = null;
		
		//DO SOMETHING HERE...
		//getDistance() is involved...
		
		return minDistanceKey;
	}

	private static Set<Key> getKeySet(char character)
	{
		List<Key> keyList = Arrays.asList(Key.values());
		Set<Key> characterProducingKeysSet = new HashSet<Key>();
		for(int i = 0; i < keyList.size(); i++)
		{
			Key key = keyList.get(i);
			assert key != null : "key is null!";
			boolean keyProducesCharacter = (key.getNormalCharacter() != null && key.getNormalCharacter() == character) || (key.getShiftModifiedCharacter() != null && key.getShiftModifiedCharacter() == character);
			if(keyProducesCharacter) characterProducingKeysSet.add(key);
		}
		return characterProducingKeysSet;
	}
	
	private static Map<Key, Set<Key>> getKeyToNeighborMap_QWERTY()
	{
		Map<Key, Set<Key>> keyToNeighborSetMap = new HashMap<Key, Set<Key>>();		
		//Produce keyToNeighborSetMap here
		//You might want to take a look at getSet()
		keyToNeighborSetMap.put(A, getSet(Q, W, S, SHIFT_1, Z));
		keyToNeighborSetMap.put(B, getSet(G, H, N, V, SPACEBAR_3));
		keyToNeighborSetMap.put(C, getSet(X, V, D, F, SPACEBAR_1));
		keyToNeighborSetMap.put(D, getSet(E, R, F, S, X, C));
		keyToNeighborSetMap.put(E, getSet(W, S, D, F, R, THREE, FOUR));
		keyToNeighborSetMap.put(F, getSet(R, T, G, D, C, V));
		keyToNeighborSetMap.put(G, getSet(F, V, B, H, Y, T));
		keyToNeighborSetMap.put(H, getSet(G, B, N, J, U, Y));
		keyToNeighborSetMap.put(I, getSet(U, J, K, O, EIGHT, NINE));
		keyToNeighborSetMap.put(J, getSet(U, H, N, M, K, I));
		keyToNeighborSetMap.put(K, getSet(J, L, I, O, M, COMMA));
		keyToNeighborSetMap.put(L, getSet(K, SEMICOLON, O, P, COMMA, PERIOD));
		keyToNeighborSetMap.put(M, getSet(N, COMMA, J, K, SPACEBAR_1));
		keyToNeighborSetMap.put(N, getSet(B, M, H, J, SPACEBAR_4));
		keyToNeighborSetMap.put(O, getSet(I, P, K, L, NINE, ZERO));
		keyToNeighborSetMap.put(P, getSet(O, L, SEMICOLON, LEFT_BRACKET, ZERO, MINUS));
		keyToNeighborSetMap.put(Q, getSet(ONE, TWO, TAB, W, A));
		keyToNeighborSetMap.put(R, getSet(E, D, F, T, FOUR, FIVE));
		keyToNeighborSetMap.put(S, getSet(A, Z, X, D, E, W));
		keyToNeighborSetMap.put(T, getSet(R, F, G, Y, FIVE, SIX));
		keyToNeighborSetMap.put(U, getSet(Y, H, J, I, SEVEN, EIGHT));
		keyToNeighborSetMap.put(V, getSet(C, F, G, B, SPACEBAR_2));
		keyToNeighborSetMap.put(W, getSet(Q, A, S, E, TWO, THREE));
		keyToNeighborSetMap.put(X, getSet(Z, S, D, C));
		keyToNeighborSetMap.put(Y, getSet(T, G, H, U, SIX, SEVEN));
		keyToNeighborSetMap.put(Z, getSet(SHIFT_1, A, S, X));
		keyToNeighborSetMap.put(ONE, getSet(TWO, Q, TAB, BACKTICK));
		keyToNeighborSetMap.put(TWO, getSet(ONE, Q, W, THREE));
		keyToNeighborSetMap.put(THREE, getSet(TWO, FOUR, W, E));
		keyToNeighborSetMap.put(FOUR, getSet(FIVE, THREE, E, R));
		keyToNeighborSetMap.put(FIVE, getSet(FOUR, SIX, R, T));
		keyToNeighborSetMap.put(SIX, getSet(FIVE, SEVEN, T, Y));
		keyToNeighborSetMap.put(SEVEN, getSet(SIX, EIGHT, Y, U));
		keyToNeighborSetMap.put(EIGHT, getSet(SEVEN, NINE, U, I));
		keyToNeighborSetMap.put(NINE, getSet(EIGHT, ZERO, I, O));
		keyToNeighborSetMap.put(ZERO, getSet(MINUS, NINE, O, P));
		keyToNeighborSetMap.put(MINUS, getSet(ZERO, EQUALS, P, LEFT_BRACKET));
		keyToNeighborSetMap.put(EQUALS, getSet(MINUS, LEFT_BRACKET, RIGHT_BRACKET));
		keyToNeighborSetMap.put(BACKTICK, getSet(TAB, ONE));
		keyToNeighborSetMap.put(TAB, getSet(Q, ONE, BACKTICK));
		keyToNeighborSetMap.put(LEFT_BRACKET, getSet(RIGHT_BRACKET, P, MINUS, EQUALS));
		keyToNeighborSetMap.put(RIGHT_BRACKET, getSet(LEFT_BRACKET, BACKSLASH, EQUALS, TICK));
		keyToNeighborSetMap.put(BACKSLASH, getSet(RETURN, RIGHT_BRACKET));
		keyToNeighborSetMap.put(SEMICOLON, getSet(L, TICK, P, PERIOD, FORESLASH));
		keyToNeighborSetMap.put(TICK, getSet(LEFT_BRACKET, RIGHT_BRACKET, SEMICOLON, FORESLASH));
		keyToNeighborSetMap.put(SHIFT_1, getSet(Z, A));
		keyToNeighborSetMap.put(COMMA, getSet(M, K, L, PERIOD));
		keyToNeighborSetMap.put(PERIOD, getSet(COMMA, L, SEMICOLON, FORESLASH));
		keyToNeighborSetMap.put(FORESLASH, getSet(SEMICOLON, TICK, PERIOD, SHIFT_2));
		keyToNeighborSetMap.put(SHIFT_2, getSet(FORESLASH, TICK));
		keyToNeighborSetMap.put(RETURN, getSet(SHIFT_2, TICK, RIGHT_BRACKET, BACKSLASH));
		keyToNeighborSetMap.put(SPACEBAR_1, getSet(C));
		keyToNeighborSetMap.put(SPACEBAR_2, getSet(V));
		keyToNeighborSetMap.put(SPACEBAR_3, getSet(B));
		keyToNeighborSetMap.put(SPACEBAR_4, getSet(N));
		keyToNeighborSetMap.put(SPACEBAR_5, getSet(M));	
		return keyToNeighborSetMap;
	}
	
	private static Set<Key> getSet(Key... keys)
	{
		return new HashSet<Key>(Arrays.asList(keys));
	}
	
	private Key[] getStringToKeyArray_QWERTY(String string)
	{
		Key[] stringToKeyArray = new Key[string.length()];
		int nextArrayIndex = 0;
		
		// turn every char into a Key and append them all into the array
		// if the char is a space, find the one closest to the last char, and then append to array
		for(int i = 0; i < string.length(); i++)
		{
			if(string.charAt(i)==Key.A.getShiftModifiedCharacter() || string.charAt(i)==Key.A.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.A;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.B.getShiftModifiedCharacter() || string.charAt(i)==Key.B.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.B;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.C.getShiftModifiedCharacter() || string.charAt(i)==Key.C.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.C;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.D.getShiftModifiedCharacter() || string.charAt(i)==Key.D.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.D;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.E.getShiftModifiedCharacter() || string.charAt(i)==Key.E.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.E;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.F.getShiftModifiedCharacter() || string.charAt(i)==Key.F.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.F;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.G.getShiftModifiedCharacter() || string.charAt(i)==Key.G.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.G;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.H.getShiftModifiedCharacter() || string.charAt(i)==Key.H.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.H;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.I.getShiftModifiedCharacter() || string.charAt(i)==Key.I.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.I;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.J.getShiftModifiedCharacter() || string.charAt(i)==Key.J.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.J;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.K.getShiftModifiedCharacter() || string.charAt(i)==Key.K.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.K;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.L.getShiftModifiedCharacter() || string.charAt(i)==Key.L.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.L;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.M.getShiftModifiedCharacter() || string.charAt(i)==Key.M.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.M;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.N.getShiftModifiedCharacter() || string.charAt(i)==Key.N.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.N;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.O.getShiftModifiedCharacter() || string.charAt(i)==Key.O.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.O;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.P.getShiftModifiedCharacter() || string.charAt(i)==Key.P.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.P;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.Q.getShiftModifiedCharacter() || string.charAt(i)==Key.Q.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.Q;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.R.getShiftModifiedCharacter() || string.charAt(i)==Key.R.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.R;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.S.getShiftModifiedCharacter() || string.charAt(i)==Key.S.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.S;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.T.getShiftModifiedCharacter() || string.charAt(i)==Key.T.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.T;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.U.getShiftModifiedCharacter() || string.charAt(i)==Key.U.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.U;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.V.getShiftModifiedCharacter() || string.charAt(i)==Key.V.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.V;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.W.getShiftModifiedCharacter() || string.charAt(i)==Key.W.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.W;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.X.getShiftModifiedCharacter() || string.charAt(i)==Key.X.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.X;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.Y.getShiftModifiedCharacter() || string.charAt(i)==Key.Y.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.Y;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.Z.getShiftModifiedCharacter() || string.charAt(i)==Key.Z.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.Z;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.BACKTICK.getShiftModifiedCharacter() || string.charAt(i)==Key.BACKTICK.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.BACKTICK;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.ONE.getShiftModifiedCharacter() || string.charAt(i)==Key.ONE.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.ONE;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.TWO.getShiftModifiedCharacter() || string.charAt(i)==Key.TWO.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.TWO;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.THREE.getShiftModifiedCharacter() || string.charAt(i)==Key.THREE.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.THREE;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.FOUR.getShiftModifiedCharacter() || string.charAt(i)==Key.FOUR.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.FOUR;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.FIVE.getShiftModifiedCharacter() || string.charAt(i)==Key.FIVE.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.FIVE;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.SIX.getShiftModifiedCharacter() || string.charAt(i)==Key.SIX.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.SIX;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.SEVEN.getShiftModifiedCharacter() || string.charAt(i)==Key.SEVEN.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.SEVEN;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.EIGHT.getShiftModifiedCharacter() || string.charAt(i)==Key.EIGHT.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.EIGHT;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.NINE.getShiftModifiedCharacter() || string.charAt(i)==Key.NINE.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.NINE;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.ZERO.getShiftModifiedCharacter() || string.charAt(i)==Key.ZERO.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.ZERO;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.MINUS.getShiftModifiedCharacter() || string.charAt(i)==Key.MINUS.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.MINUS;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.EQUALS.getShiftModifiedCharacter() || string.charAt(i)==Key.EQUALS.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.EQUALS;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.TAB.getShiftModifiedCharacter() || string.charAt(i)==Key.TAB.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.TAB;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.LEFT_BRACKET.getShiftModifiedCharacter() || string.charAt(i)==Key.LEFT_BRACKET.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.LEFT_BRACKET;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.RIGHT_BRACKET.getShiftModifiedCharacter() || string.charAt(i)==Key.RIGHT_BRACKET.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.RIGHT_BRACKET;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.BACKSLASH.getShiftModifiedCharacter() || string.charAt(i)==Key.BACKSLASH.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.BACKSLASH;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.SEMICOLON.getShiftModifiedCharacter() || string.charAt(i)==Key.SEMICOLON.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.SEMICOLON;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.TICK.getShiftModifiedCharacter() || string.charAt(i)==Key.TICK.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.TICK;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.RETURN.getShiftModifiedCharacter() || string.charAt(i)==Key.RETURN.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.RETURN;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.COMMA.getShiftModifiedCharacter() || string.charAt(i)==Key.COMMA.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.COMMA;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.PERIOD.getShiftModifiedCharacter() || string.charAt(i)==Key.PERIOD.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.PERIOD;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.FORESLASH.getShiftModifiedCharacter() || string.charAt(i)==Key.FORESLASH.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.FORESLASH;
				nextArrayIndex++;
			}
			if(string.charAt(i)== ' ')
			{
				stringToKeyArray[nextArrayIndex] = getNearestSpaceBar_QWERTY(string);
				nextArrayIndex++;
			}
		}
		return stringToKeyArray;
	}

	private Key[] getStringToKeyArray_DVORAK(String string)
	{
		Key[] stringToKeyArray = new Key[string.length()];
		int nextArrayIndex = 0;
		
		// turn every char into a Key and append them all into the array
		// if the char is a space, find the one closest to the last char, and then append to array
		for(int i = 0; i < string.length(); i++)
		{
			if(string.charAt(i)==Key.A.getShiftModifiedCharacter() || string.charAt(i)==Key.A.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.A;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.B.getShiftModifiedCharacter() || string.charAt(i)==Key.B.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.B;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.C.getShiftModifiedCharacter() || string.charAt(i)==Key.C.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.C;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.D.getShiftModifiedCharacter() || string.charAt(i)==Key.D.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.D;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.E.getShiftModifiedCharacter() || string.charAt(i)==Key.E.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.E;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.F.getShiftModifiedCharacter() || string.charAt(i)==Key.F.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.F;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.G.getShiftModifiedCharacter() || string.charAt(i)==Key.G.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.G;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.H.getShiftModifiedCharacter() || string.charAt(i)==Key.H.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.H;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.I.getShiftModifiedCharacter() || string.charAt(i)==Key.I.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.I;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.J.getShiftModifiedCharacter() || string.charAt(i)==Key.J.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.J;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.K.getShiftModifiedCharacter() || string.charAt(i)==Key.K.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.K;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.L.getShiftModifiedCharacter() || string.charAt(i)==Key.L.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.L;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.M.getShiftModifiedCharacter() || string.charAt(i)==Key.M.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.M;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.N.getShiftModifiedCharacter() || string.charAt(i)==Key.N.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.N;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.O.getShiftModifiedCharacter() || string.charAt(i)==Key.O.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.O;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.P.getShiftModifiedCharacter() || string.charAt(i)==Key.P.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.P;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.Q.getShiftModifiedCharacter() || string.charAt(i)==Key.Q.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.Q;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.R.getShiftModifiedCharacter() || string.charAt(i)==Key.R.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.R;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.S.getShiftModifiedCharacter() || string.charAt(i)==Key.S.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.S;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.T.getShiftModifiedCharacter() || string.charAt(i)==Key.T.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.T;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.U.getShiftModifiedCharacter() || string.charAt(i)==Key.U.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.U;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.V.getShiftModifiedCharacter() || string.charAt(i)==Key.V.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.V;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.W.getShiftModifiedCharacter() || string.charAt(i)==Key.W.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.W;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.X.getShiftModifiedCharacter() || string.charAt(i)==Key.X.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.X;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.Y.getShiftModifiedCharacter() || string.charAt(i)==Key.Y.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.Y;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.Z.getShiftModifiedCharacter() || string.charAt(i)==Key.Z.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.Z;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.BACKTICK.getShiftModifiedCharacter() || string.charAt(i)==Key.BACKTICK.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.BACKTICK;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.ONE.getShiftModifiedCharacter() || string.charAt(i)==Key.ONE.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.ONE;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.TWO.getShiftModifiedCharacter() || string.charAt(i)==Key.TWO.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.TWO;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.THREE.getShiftModifiedCharacter() || string.charAt(i)==Key.THREE.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.THREE;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.FOUR.getShiftModifiedCharacter() || string.charAt(i)==Key.FOUR.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.FOUR;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.FIVE.getShiftModifiedCharacter() || string.charAt(i)==Key.FIVE.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.FIVE;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.SIX.getShiftModifiedCharacter() || string.charAt(i)==Key.SIX.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.SIX;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.SEVEN.getShiftModifiedCharacter() || string.charAt(i)==Key.SEVEN.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.SEVEN;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.EIGHT.getShiftModifiedCharacter() || string.charAt(i)==Key.EIGHT.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.EIGHT;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.NINE.getShiftModifiedCharacter() || string.charAt(i)==Key.NINE.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.NINE;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.ZERO.getShiftModifiedCharacter() || string.charAt(i)==Key.ZERO.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.ZERO;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.MINUS.getShiftModifiedCharacter() || string.charAt(i)==Key.MINUS.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.MINUS;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.EQUALS.getShiftModifiedCharacter() || string.charAt(i)==Key.EQUALS.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.EQUALS;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.TAB.getShiftModifiedCharacter() || string.charAt(i)==Key.TAB.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.TAB;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.LEFT_BRACKET.getShiftModifiedCharacter() || string.charAt(i)==Key.LEFT_BRACKET.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.LEFT_BRACKET;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.RIGHT_BRACKET.getShiftModifiedCharacter() || string.charAt(i)==Key.RIGHT_BRACKET.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.RIGHT_BRACKET;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.BACKSLASH.getShiftModifiedCharacter() || string.charAt(i)==Key.BACKSLASH.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.BACKSLASH;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.SEMICOLON.getShiftModifiedCharacter() || string.charAt(i)==Key.SEMICOLON.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.SEMICOLON;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.TICK.getShiftModifiedCharacter() || string.charAt(i)==Key.TICK.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.TICK;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.RETURN.getShiftModifiedCharacter() || string.charAt(i)==Key.RETURN.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.RETURN;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.COMMA.getShiftModifiedCharacter() || string.charAt(i)==Key.COMMA.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.COMMA;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.PERIOD.getShiftModifiedCharacter() || string.charAt(i)==Key.PERIOD.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.PERIOD;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.FORESLASH.getShiftModifiedCharacter() || string.charAt(i)==Key.FORESLASH.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.FORESLASH;
				nextArrayIndex++;
			}
			if(string.charAt(i)== ' ')
			{
				stringToKeyArray[nextArrayIndex] = getNearestSpaceBar_DVORAK(string);
				nextArrayIndex++;
			}
		}
		return stringToKeyArray;
	}
	
	private Key[] getStringToKeyArray_ROT13(String string)
	{
		Key[] stringToKeyArray = new Key[string.length()];
		int nextArrayIndex = 0;
		
		// turn every char into a Key and append them all into the array
		// if the char is a space, find the one closest to the last char, and then append to array
		for(int i = 0; i < string.length(); i++)
		{
			if(string.charAt(i)==Key.A.getShiftModifiedCharacter() || string.charAt(i)==Key.A.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.A;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.B.getShiftModifiedCharacter() || string.charAt(i)==Key.B.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.B;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.C.getShiftModifiedCharacter() || string.charAt(i)==Key.C.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.C;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.D.getShiftModifiedCharacter() || string.charAt(i)==Key.D.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.D;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.E.getShiftModifiedCharacter() || string.charAt(i)==Key.E.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.E;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.F.getShiftModifiedCharacter() || string.charAt(i)==Key.F.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.F;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.G.getShiftModifiedCharacter() || string.charAt(i)==Key.G.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.G;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.H.getShiftModifiedCharacter() || string.charAt(i)==Key.H.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.H;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.I.getShiftModifiedCharacter() || string.charAt(i)==Key.I.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.I;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.J.getShiftModifiedCharacter() || string.charAt(i)==Key.J.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.J;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.K.getShiftModifiedCharacter() || string.charAt(i)==Key.K.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.K;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.L.getShiftModifiedCharacter() || string.charAt(i)==Key.L.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.L;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.M.getShiftModifiedCharacter() || string.charAt(i)==Key.M.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.M;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.N.getShiftModifiedCharacter() || string.charAt(i)==Key.N.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.N;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.O.getShiftModifiedCharacter() || string.charAt(i)==Key.O.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.O;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.P.getShiftModifiedCharacter() || string.charAt(i)==Key.P.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.P;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.Q.getShiftModifiedCharacter() || string.charAt(i)==Key.Q.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.Q;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.R.getShiftModifiedCharacter() || string.charAt(i)==Key.R.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.R;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.S.getShiftModifiedCharacter() || string.charAt(i)==Key.S.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.S;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.T.getShiftModifiedCharacter() || string.charAt(i)==Key.T.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.T;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.U.getShiftModifiedCharacter() || string.charAt(i)==Key.U.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.U;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.V.getShiftModifiedCharacter() || string.charAt(i)==Key.V.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.V;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.W.getShiftModifiedCharacter() || string.charAt(i)==Key.W.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.W;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.X.getShiftModifiedCharacter() || string.charAt(i)==Key.X.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.X;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.Y.getShiftModifiedCharacter() || string.charAt(i)==Key.Y.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.Y;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.Z.getShiftModifiedCharacter() || string.charAt(i)==Key.Z.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.Z;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.BACKTICK.getShiftModifiedCharacter() || string.charAt(i)==Key.BACKTICK.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.BACKTICK;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.ONE.getShiftModifiedCharacter() || string.charAt(i)==Key.ONE.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.ONE;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.TWO.getShiftModifiedCharacter() || string.charAt(i)==Key.TWO.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.TWO;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.THREE.getShiftModifiedCharacter() || string.charAt(i)==Key.THREE.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.THREE;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.FOUR.getShiftModifiedCharacter() || string.charAt(i)==Key.FOUR.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.FOUR;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.FIVE.getShiftModifiedCharacter() || string.charAt(i)==Key.FIVE.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.FIVE;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.SIX.getShiftModifiedCharacter() || string.charAt(i)==Key.SIX.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.SIX;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.SEVEN.getShiftModifiedCharacter() || string.charAt(i)==Key.SEVEN.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.SEVEN;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.EIGHT.getShiftModifiedCharacter() || string.charAt(i)==Key.EIGHT.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.EIGHT;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.NINE.getShiftModifiedCharacter() || string.charAt(i)==Key.NINE.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.NINE;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.ZERO.getShiftModifiedCharacter() || string.charAt(i)==Key.ZERO.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.ZERO;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.MINUS.getShiftModifiedCharacter() || string.charAt(i)==Key.MINUS.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.MINUS;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.EQUALS.getShiftModifiedCharacter() || string.charAt(i)==Key.EQUALS.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.EQUALS;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.TAB.getShiftModifiedCharacter() || string.charAt(i)==Key.TAB.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.TAB;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.LEFT_BRACKET.getShiftModifiedCharacter() || string.charAt(i)==Key.LEFT_BRACKET.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.LEFT_BRACKET;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.RIGHT_BRACKET.getShiftModifiedCharacter() || string.charAt(i)==Key.RIGHT_BRACKET.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.RIGHT_BRACKET;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.BACKSLASH.getShiftModifiedCharacter() || string.charAt(i)==Key.BACKSLASH.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.BACKSLASH;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.SEMICOLON.getShiftModifiedCharacter() || string.charAt(i)==Key.SEMICOLON.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.SEMICOLON;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.TICK.getShiftModifiedCharacter() || string.charAt(i)==Key.TICK.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.TICK;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.RETURN.getShiftModifiedCharacter() || string.charAt(i)==Key.RETURN.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.RETURN;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.COMMA.getShiftModifiedCharacter() || string.charAt(i)==Key.COMMA.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.COMMA;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.PERIOD.getShiftModifiedCharacter() || string.charAt(i)==Key.PERIOD.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.PERIOD;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.FORESLASH.getShiftModifiedCharacter() || string.charAt(i)==Key.FORESLASH.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.FORESLASH;
				nextArrayIndex++;
			}
			if(string.charAt(i)== ' ')
			{
				stringToKeyArray[nextArrayIndex] = getNearestSpaceBar_ROT13(string);
				//System.out.println("Closest space bar is: " + stringToKeyArray[nextArrayIndex]);
				nextArrayIndex++;
			}
		}
		return stringToKeyArray;
	}
	
	private Key[] getStringToKeyArray_COLEMAK(String string)
	{
		Key[] stringToKeyArray = new Key[string.length()];
		int nextArrayIndex = 0;
		
		// turn every char into a Key and append them all into the array
		// if the char is a space, find the one closest to the last char, and then append to array
		for(int i = 0; i < string.length(); i++)
		{
			if(string.charAt(i)==Key.A.getShiftModifiedCharacter() || string.charAt(i)==Key.A.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.A;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.B.getShiftModifiedCharacter() || string.charAt(i)==Key.B.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.B;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.C.getShiftModifiedCharacter() || string.charAt(i)==Key.C.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.C;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.D.getShiftModifiedCharacter() || string.charAt(i)==Key.D.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.D;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.E.getShiftModifiedCharacter() || string.charAt(i)==Key.E.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.E;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.F.getShiftModifiedCharacter() || string.charAt(i)==Key.F.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.F;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.G.getShiftModifiedCharacter() || string.charAt(i)==Key.G.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.G;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.H.getShiftModifiedCharacter() || string.charAt(i)==Key.H.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.H;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.I.getShiftModifiedCharacter() || string.charAt(i)==Key.I.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.I;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.J.getShiftModifiedCharacter() || string.charAt(i)==Key.J.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.J;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.K.getShiftModifiedCharacter() || string.charAt(i)==Key.K.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.K;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.L.getShiftModifiedCharacter() || string.charAt(i)==Key.L.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.L;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.M.getShiftModifiedCharacter() || string.charAt(i)==Key.M.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.M;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.N.getShiftModifiedCharacter() || string.charAt(i)==Key.N.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.N;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.O.getShiftModifiedCharacter() || string.charAt(i)==Key.O.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.O;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.P.getShiftModifiedCharacter() || string.charAt(i)==Key.P.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.P;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.Q.getShiftModifiedCharacter() || string.charAt(i)==Key.Q.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.Q;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.R.getShiftModifiedCharacter() || string.charAt(i)==Key.R.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.R;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.S.getShiftModifiedCharacter() || string.charAt(i)==Key.S.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.S;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.T.getShiftModifiedCharacter() || string.charAt(i)==Key.T.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.T;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.U.getShiftModifiedCharacter() || string.charAt(i)==Key.U.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.U;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.V.getShiftModifiedCharacter() || string.charAt(i)==Key.V.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.V;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.W.getShiftModifiedCharacter() || string.charAt(i)==Key.W.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.W;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.X.getShiftModifiedCharacter() || string.charAt(i)==Key.X.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.X;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.Y.getShiftModifiedCharacter() || string.charAt(i)==Key.Y.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.Y;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.Z.getShiftModifiedCharacter() || string.charAt(i)==Key.Z.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.Z;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.BACKTICK.getShiftModifiedCharacter() || string.charAt(i)==Key.BACKTICK.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.BACKTICK;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.ONE.getShiftModifiedCharacter() || string.charAt(i)==Key.ONE.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.ONE;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.TWO.getShiftModifiedCharacter() || string.charAt(i)==Key.TWO.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.TWO;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.THREE.getShiftModifiedCharacter() || string.charAt(i)==Key.THREE.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.THREE;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.FOUR.getShiftModifiedCharacter() || string.charAt(i)==Key.FOUR.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.FOUR;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.FIVE.getShiftModifiedCharacter() || string.charAt(i)==Key.FIVE.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.FIVE;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.SIX.getShiftModifiedCharacter() || string.charAt(i)==Key.SIX.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.SIX;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.SEVEN.getShiftModifiedCharacter() || string.charAt(i)==Key.SEVEN.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.SEVEN;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.EIGHT.getShiftModifiedCharacter() || string.charAt(i)==Key.EIGHT.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.EIGHT;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.NINE.getShiftModifiedCharacter() || string.charAt(i)==Key.NINE.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.NINE;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.ZERO.getShiftModifiedCharacter() || string.charAt(i)==Key.ZERO.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.ZERO;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.MINUS.getShiftModifiedCharacter() || string.charAt(i)==Key.MINUS.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.MINUS;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.EQUALS.getShiftModifiedCharacter() || string.charAt(i)==Key.EQUALS.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.EQUALS;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.TAB.getShiftModifiedCharacter() || string.charAt(i)==Key.TAB.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.TAB;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.LEFT_BRACKET.getShiftModifiedCharacter() || string.charAt(i)==Key.LEFT_BRACKET.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.LEFT_BRACKET;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.RIGHT_BRACKET.getShiftModifiedCharacter() || string.charAt(i)==Key.RIGHT_BRACKET.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.RIGHT_BRACKET;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.BACKSLASH.getShiftModifiedCharacter() || string.charAt(i)==Key.BACKSLASH.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.BACKSLASH;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.SEMICOLON.getShiftModifiedCharacter() || string.charAt(i)==Key.SEMICOLON.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.SEMICOLON;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.TICK.getShiftModifiedCharacter() || string.charAt(i)==Key.TICK.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.TICK;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.RETURN.getShiftModifiedCharacter() || string.charAt(i)==Key.RETURN.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.RETURN;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.COMMA.getShiftModifiedCharacter() || string.charAt(i)==Key.COMMA.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.COMMA;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.PERIOD.getShiftModifiedCharacter() || string.charAt(i)==Key.PERIOD.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.PERIOD;
				nextArrayIndex++;
			}
			if(string.charAt(i)==Key.FORESLASH.getShiftModifiedCharacter() || string.charAt(i)==Key.FORESLASH.getNormalCharacter())
			{
				stringToKeyArray[nextArrayIndex] = Key.FORESLASH;
				nextArrayIndex++;
			}
			if(string.charAt(i)== ' ')
			{
				stringToKeyArray[nextArrayIndex] = getNearestSpaceBar_COLEMAK(string);
				nextArrayIndex++;
			}
		}
		return stringToKeyArray;
	}
	
	private Key charToKey(char character)
	{
		Key returnKey = Key.J;
		
		if(character==Key.A.getShiftModifiedCharacter() || character==Key.A.getNormalCharacter())
		{
			returnKey = Key.A;
		}
		if(character==Key.B.getShiftModifiedCharacter() || character==Key.B.getNormalCharacter())
		{
			returnKey = Key.B;
		}
		if(character==Key.C.getShiftModifiedCharacter() || character==Key.C.getNormalCharacter())
		{
			returnKey = Key.C;
		}
		if(character==Key.D.getShiftModifiedCharacter() || character==Key.D.getNormalCharacter())
		{
			returnKey = Key.D;
		}
		if(character==Key.E.getShiftModifiedCharacter() || character==Key.E.getNormalCharacter())
		{
			returnKey = Key.E;
		}
		if(character==Key.F.getShiftModifiedCharacter() || character==Key.F.getNormalCharacter())
		{
			returnKey = Key.F;
		}
		if(character==Key.G.getShiftModifiedCharacter() || character==Key.G.getNormalCharacter())
		{
			returnKey = Key.G;
		}
		if(character==Key.H.getShiftModifiedCharacter() || character==Key.H.getNormalCharacter())
		{
			returnKey = Key.H;
		}
		if(character==Key.I.getShiftModifiedCharacter() || character==Key.I.getNormalCharacter())
		{
			returnKey = Key.I;
		}
		if(character==Key.J.getShiftModifiedCharacter() || character==Key.J.getNormalCharacter())
		{
			returnKey = Key.J;
		}
		if(character==Key.K.getShiftModifiedCharacter() || character==Key.K.getNormalCharacter())
		{
			returnKey = Key.K;
		}
		if(character==Key.L.getShiftModifiedCharacter() || character==Key.L.getNormalCharacter())
		{
			returnKey = Key.L;
		}
		if(character==Key.M.getShiftModifiedCharacter() || character==Key.M.getNormalCharacter())
		{
			returnKey = Key.M;
		}
		if(character==Key.N.getShiftModifiedCharacter() || character==Key.N.getNormalCharacter())
		{
			returnKey = Key.N;
		}
		if(character==Key.O.getShiftModifiedCharacter() || character==Key.O.getNormalCharacter())
		{
			returnKey = Key.O;
		}
		if(character==Key.P.getShiftModifiedCharacter() || character==Key.P.getNormalCharacter())
		{
			returnKey = Key.P;
		}
		if(character==Key.Q.getShiftModifiedCharacter() || character==Key.Q.getNormalCharacter())
		{
			returnKey = Key.Q;
		}
		if(character==Key.R.getShiftModifiedCharacter() || character==Key.R.getNormalCharacter())
		{
			returnKey = Key.R;
		}
		if(character==Key.S.getShiftModifiedCharacter() || character==Key.S.getNormalCharacter())
		{
			returnKey = Key.S;
		}
		if(character==Key.T.getShiftModifiedCharacter() || character==Key.T.getNormalCharacter())
		{
			returnKey = Key.T;
		}
		if(character==Key.U.getShiftModifiedCharacter() || character==Key.U.getNormalCharacter())
		{
			returnKey = Key.U;
		}
		if(character==Key.V.getShiftModifiedCharacter() || character==Key.V.getNormalCharacter())
		{
			returnKey = Key.V;
		}
		if(character==Key.W.getShiftModifiedCharacter() || character==Key.W.getNormalCharacter())
		{
			returnKey = Key.W;
		}
		if(character==Key.X.getShiftModifiedCharacter() || character==Key.X.getNormalCharacter())
		{
			returnKey = Key.X;
		}
		if(character==Key.Y.getShiftModifiedCharacter() || character==Key.Y.getNormalCharacter())
		{
			returnKey = Key.Y;
		}
		if(character==Key.Z.getShiftModifiedCharacter() || character==Key.Z.getNormalCharacter())
		{
			returnKey = Key.Z;
		}
		if(character==Key.BACKTICK.getShiftModifiedCharacter() || character==Key.BACKTICK.getNormalCharacter())
		{
			returnKey = Key.BACKTICK;
		}
		if(character==Key.ONE.getShiftModifiedCharacter() || character==Key.ONE.getNormalCharacter())
		{
			returnKey = Key.ONE;
		}
		if(character==Key.TWO.getShiftModifiedCharacter() || character==Key.TWO.getNormalCharacter())
		{
			returnKey = Key.TWO;
		}
		if(character==Key.THREE.getShiftModifiedCharacter() || character==Key.THREE.getNormalCharacter())
		{
			returnKey = Key.THREE;
		}
		if(character==Key.FOUR.getShiftModifiedCharacter() || character==Key.FOUR.getNormalCharacter())
		{
			returnKey = Key.FOUR;
		}
		if(character==Key.FIVE.getShiftModifiedCharacter() || character==Key.FIVE.getNormalCharacter())
		{
			returnKey = Key.FIVE;
		}
		if(character==Key.SIX.getShiftModifiedCharacter() || character==Key.SIX.getNormalCharacter())
		{
			returnKey = Key.SIX;
		}
		if(character==Key.SEVEN.getShiftModifiedCharacter() || character==Key.SEVEN.getNormalCharacter())
		{
			returnKey = Key.SEVEN;
		}
		if(character==Key.EIGHT.getShiftModifiedCharacter() || character==Key.EIGHT.getNormalCharacter())
		{
			returnKey = Key.EIGHT;
		}
		if(character==Key.NINE.getShiftModifiedCharacter() || character==Key.NINE.getNormalCharacter())
		{
			returnKey = Key.NINE;
		}
		if(character==Key.ZERO.getShiftModifiedCharacter() || character==Key.ZERO.getNormalCharacter())
		{
			returnKey = Key.ZERO;
		}
		if(character==Key.MINUS.getShiftModifiedCharacter() || character==Key.MINUS.getNormalCharacter())
		{
			returnKey = Key.MINUS;
		}
		if(character==Key.EQUALS.getShiftModifiedCharacter() || character==Key.EQUALS.getNormalCharacter())
		{
			returnKey = Key.EQUALS;
		}
		if(character==Key.TAB.getShiftModifiedCharacter() || character==Key.TAB.getNormalCharacter())
		{
			returnKey = Key.TAB;
		}
		if(character==Key.LEFT_BRACKET.getShiftModifiedCharacter() || character==Key.LEFT_BRACKET.getNormalCharacter())
		{
			returnKey = Key.LEFT_BRACKET;
		}
		if(character==Key.RIGHT_BRACKET.getShiftModifiedCharacter() || character==Key.RIGHT_BRACKET.getNormalCharacter())
		{
			returnKey = Key.RIGHT_BRACKET;
		}
		if(character==Key.BACKSLASH.getShiftModifiedCharacter() || character==Key.BACKSLASH.getNormalCharacter())
		{
			returnKey = Key.BACKSLASH;
		}
		if(character==Key.SEMICOLON.getShiftModifiedCharacter() || character==Key.SEMICOLON.getNormalCharacter())
		{
			returnKey = Key.SEMICOLON;
		}
		if(character==Key.TICK.getShiftModifiedCharacter() || character==Key.TICK.getNormalCharacter())
		{
			returnKey = Key.TICK;
		}
		if(character==Key.RETURN.getShiftModifiedCharacter() || character==Key.RETURN.getNormalCharacter())
		{
			returnKey = Key.RETURN;
		}
		if(character==Key.COMMA.getShiftModifiedCharacter() || character==Key.COMMA.getNormalCharacter())
		{
			returnKey = Key.COMMA;
		}
		if(character==Key.PERIOD.getShiftModifiedCharacter() || character==Key.PERIOD.getNormalCharacter())
		{
			returnKey = Key.PERIOD;
		}
		if(character==Key.FORESLASH.getShiftModifiedCharacter() || character==Key.FORESLASH.getNormalCharacter())
		{
			returnKey = Key.FORESLASH;
		}
		
		return returnKey;
	}
	private Key getNearestSpaceBar_QWERTY(String string)
	{
		int spaceBarIndex = 0;
		int indexBeforeSpaceBar = 0;
		int distanceToSpaceBar1 = 0;
		int distanceToSpaceBar2 = 0;
		int distanceToSpaceBar3 = 0;
		int distanceToSpaceBar4 = 0;
		int distanceToSpaceBar5 = 0;
		Key keyBeforeSpaceBar = J;
		Key retSpaceBarKey = SPACEBAR_4;
		
		for(int i = 0; i < string.length(); i++)
		{
			if(string.charAt(i) == ' ')
			{
				spaceBarIndex = i;
				if(spaceBarIndex == 0)
				{
					retSpaceBarKey = SPACEBAR_4;
					break;
				}
				
				else
				{
					indexBeforeSpaceBar = i - 1;
				}
			}
		}
		keyBeforeSpaceBar = charToKey(string.charAt(indexBeforeSpaceBar));
		distanceToSpaceBar1 = distanceBetweenTwoKeys_QWERTY(keyBeforeSpaceBar, SPACEBAR_1);
		distanceToSpaceBar2 = distanceBetweenTwoKeys_QWERTY(keyBeforeSpaceBar, SPACEBAR_2);
		distanceToSpaceBar3 = distanceBetweenTwoKeys_QWERTY(keyBeforeSpaceBar, SPACEBAR_3);
		distanceToSpaceBar4 = distanceBetweenTwoKeys_QWERTY(keyBeforeSpaceBar, SPACEBAR_4);
		distanceToSpaceBar5 = distanceBetweenTwoKeys_QWERTY(keyBeforeSpaceBar, SPACEBAR_5);
		
		int[] distancesToSpaceBars = new int[5];
		distancesToSpaceBars[0] = distanceToSpaceBar1;
		distancesToSpaceBars[1] = distanceToSpaceBar2;
		distancesToSpaceBars[2] = distanceToSpaceBar3;
		distancesToSpaceBars[3] = distanceToSpaceBar4;
		distancesToSpaceBars[4] = distanceToSpaceBar5;
		
		int nearestSpaceBarDistance = getMin(distancesToSpaceBars);
		int nearestSpaceBar = 0;
		
		for(int i = 0; i < distancesToSpaceBars.length; i++)
		{
			if(distancesToSpaceBars[i] == nearestSpaceBarDistance)
			{
				nearestSpaceBar = i;
			}
		}
		
		if(nearestSpaceBar == 0) {retSpaceBarKey = SPACEBAR_1;}
		else if(nearestSpaceBar == 1) {retSpaceBarKey = SPACEBAR_2;}
		else if(nearestSpaceBar == 2) {retSpaceBarKey = SPACEBAR_3;}
		else if(nearestSpaceBar == 3) {retSpaceBarKey = SPACEBAR_4;}
		else if(nearestSpaceBar == 4) {retSpaceBarKey = SPACEBAR_5;}

		return retSpaceBarKey;
		
	}
	private Key getNearestSpaceBar_DVORAK(String string)
	{
		int spaceBarIndex = 0;
		int indexBeforeSpaceBar = 0;
		int distanceToSpaceBar1 = 0;
		int distanceToSpaceBar2 = 0;
		int distanceToSpaceBar3 = 0;
		int distanceToSpaceBar4 = 0;
		int distanceToSpaceBar5 = 0;
		Key keyBeforeSpaceBar = H;
		Key retSpaceBarKey = SPACEBAR_4;
		
		for(int i = 0; i < string.length(); i++)
		{
			if(string.charAt(i) == ' ')
			{
				spaceBarIndex = i;
				if(spaceBarIndex == 0)
				{
					retSpaceBarKey = SPACEBAR_4;
					break;
				}
				
				else
				{
					indexBeforeSpaceBar = i - 1;
				}
			}
		}
		keyBeforeSpaceBar = charToKey(string.charAt(indexBeforeSpaceBar));
		distanceToSpaceBar1 = distanceBetweenTwoKeys_DVORAK(keyBeforeSpaceBar, SPACEBAR_1);
		distanceToSpaceBar2 = distanceBetweenTwoKeys_DVORAK(keyBeforeSpaceBar, SPACEBAR_2);
		distanceToSpaceBar3 = distanceBetweenTwoKeys_DVORAK(keyBeforeSpaceBar, SPACEBAR_3);
		distanceToSpaceBar4 = distanceBetweenTwoKeys_DVORAK(keyBeforeSpaceBar, SPACEBAR_4);
		distanceToSpaceBar5 = distanceBetweenTwoKeys_DVORAK(keyBeforeSpaceBar, SPACEBAR_5);
		
		int[] distancesToSpaceBars = new int[5];
		distancesToSpaceBars[0] = distanceToSpaceBar1;
		distancesToSpaceBars[1] = distanceToSpaceBar2;
		distancesToSpaceBars[2] = distanceToSpaceBar3;
		distancesToSpaceBars[3] = distanceToSpaceBar4;
		distancesToSpaceBars[4] = distanceToSpaceBar5;
		
		int nearestSpaceBarDistance = getMin(distancesToSpaceBars);
		int nearestSpaceBar = 0;
		
		for(int i = 0; i < distancesToSpaceBars.length; i++)
		{
			if(distancesToSpaceBars[i] == nearestSpaceBarDistance)
			{
				nearestSpaceBar = i;
			}
		}
		
		if(nearestSpaceBar == 0) {retSpaceBarKey = SPACEBAR_1;}
		else if(nearestSpaceBar == 1) {retSpaceBarKey = SPACEBAR_2;}
		else if(nearestSpaceBar == 2) {retSpaceBarKey = SPACEBAR_3;}
		else if(nearestSpaceBar == 3) {retSpaceBarKey = SPACEBAR_4;}
		else if(nearestSpaceBar == 4) {retSpaceBarKey = SPACEBAR_5;}

		return retSpaceBarKey;
	}
	
	private Key getNearestSpaceBar_ROT13(String string)
	{
		int spaceBarIndex = 0;
		int indexBeforeSpaceBar = 0;
		int distanceToSpaceBar1 = 0;
		int distanceToSpaceBar2 = 0;
		int distanceToSpaceBar3 = 0;
		int distanceToSpaceBar4 = 0;
		int distanceToSpaceBar5 = 0;
		Key keyBeforeSpaceBar = W;
		Key retSpaceBarKey = SPACEBAR_4;
		
		for(int i = 0; i < string.length(); i++)
		{
			if(string.charAt(i) == ' ')
			{
				spaceBarIndex = i;
				if(spaceBarIndex == 0)
				{
					retSpaceBarKey = SPACEBAR_4;
					break;
				}
				
				else
				{
					indexBeforeSpaceBar = i - 1;
					//System.out.println("Letter before space bar is: " + string.charAt(indexBeforeSpaceBar));
				}
			}
		}
		keyBeforeSpaceBar = charToKey(string.charAt(indexBeforeSpaceBar));
		distanceToSpaceBar1 = distanceBetweenTwoKeys_ROT13(keyBeforeSpaceBar, SPACEBAR_1);
		distanceToSpaceBar2 = distanceBetweenTwoKeys_ROT13(keyBeforeSpaceBar, SPACEBAR_2);
		distanceToSpaceBar3 = distanceBetweenTwoKeys_ROT13(keyBeforeSpaceBar, SPACEBAR_3);
		distanceToSpaceBar4 = distanceBetweenTwoKeys_ROT13(keyBeforeSpaceBar, SPACEBAR_4);
		distanceToSpaceBar5 = distanceBetweenTwoKeys_ROT13(keyBeforeSpaceBar, SPACEBAR_5);
		
		int[] distancesToSpaceBars = new int[5];
		distancesToSpaceBars[0] = distanceToSpaceBar1;
		distancesToSpaceBars[1] = distanceToSpaceBar2;
		distancesToSpaceBars[2] = distanceToSpaceBar3;
		distancesToSpaceBars[3] = distanceToSpaceBar4;
		distancesToSpaceBars[4] = distanceToSpaceBar5;
//		System.out.println("Distance to space bar 1 = " + distanceToSpaceBar1);
//		System.out.println("Distance to space bar 2 = " + distanceToSpaceBar2);
//		System.out.println("Distance to space bar 3 = " + distanceToSpaceBar3);
//		System.out.println("Distance to space bar 4 = " + distanceToSpaceBar4);
//		System.out.println("Distance to space bar 5 = " + distanceToSpaceBar5);
		
		int nearestSpaceBarDistance = getMin(distancesToSpaceBars);
		//System.out.println("Nearest space bar after getMin() " + nearestSpaceBarDistance);
		int nearestSpaceBar = 0;
		
		for(int i = 0; i < distancesToSpaceBars.length; i++)
		{
			if(distancesToSpaceBars[i] == nearestSpaceBarDistance)
			{
				nearestSpaceBar = i;
			}
		}
		
		if(nearestSpaceBar == 0) {retSpaceBarKey = SPACEBAR_1;}
		else if(nearestSpaceBar == 1) {retSpaceBarKey = SPACEBAR_2;}
		else if(nearestSpaceBar == 2) {retSpaceBarKey = SPACEBAR_3;}
		else if(nearestSpaceBar == 3) {retSpaceBarKey = SPACEBAR_4;}
		else if(nearestSpaceBar == 4) {retSpaceBarKey = SPACEBAR_5;}

		
		return retSpaceBarKey;
	}
	
	private Key getNearestSpaceBar_COLEMAK(String string)
	{
		int spaceBarIndex = 0;
		int indexBeforeSpaceBar = 0;
		int distanceToSpaceBar1 = 0;
		int distanceToSpaceBar2 = 0;
		int distanceToSpaceBar3 = 0;
		int distanceToSpaceBar4 = 0;
		int distanceToSpaceBar5 = 0;
		Key keyBeforeSpaceBar = N;
		Key retSpaceBarKey = SPACEBAR_4;
		
		for(int i = 0; i < string.length(); i++)
		{
			if(string.charAt(i) == ' ')
			{
				spaceBarIndex = i;
				if(spaceBarIndex == 0)
				{
					retSpaceBarKey = SPACEBAR_4;
					break;
				}
				
				else
				{
					indexBeforeSpaceBar = i - 1;
				}
			}
		}
		keyBeforeSpaceBar = charToKey(string.charAt(indexBeforeSpaceBar));
		distanceToSpaceBar1 = distanceBetweenTwoKeys_ROT13(keyBeforeSpaceBar, SPACEBAR_1);
		distanceToSpaceBar2 = distanceBetweenTwoKeys_ROT13(keyBeforeSpaceBar, SPACEBAR_2);
		distanceToSpaceBar3 = distanceBetweenTwoKeys_ROT13(keyBeforeSpaceBar, SPACEBAR_3);
		distanceToSpaceBar4 = distanceBetweenTwoKeys_ROT13(keyBeforeSpaceBar, SPACEBAR_4);
		distanceToSpaceBar5 = distanceBetweenTwoKeys_ROT13(keyBeforeSpaceBar, SPACEBAR_5);
		
		int[] distancesToSpaceBars = new int[5];
		distancesToSpaceBars[0] = distanceToSpaceBar1;
		distancesToSpaceBars[1] = distanceToSpaceBar2;
		distancesToSpaceBars[2] = distanceToSpaceBar3;
		distancesToSpaceBars[3] = distanceToSpaceBar4;
		distancesToSpaceBars[4] = distanceToSpaceBar5;
		
		int nearestSpaceBarDistance = getMin(distancesToSpaceBars);
		int nearestSpaceBar = 0;
		
		for(int i = 0; i < distancesToSpaceBars.length; i++)
		{
			if(distancesToSpaceBars[i] == nearestSpaceBarDistance)
			{
				nearestSpaceBar = i;
			}
		}
		
		if(nearestSpaceBar == 0) {retSpaceBarKey = SPACEBAR_1;}
		else if(nearestSpaceBar == 1) {retSpaceBarKey = SPACEBAR_2;}
		else if(nearestSpaceBar == 2) {retSpaceBarKey = SPACEBAR_3;}
		else if(nearestSpaceBar == 3) {retSpaceBarKey = SPACEBAR_4;}
		else if(nearestSpaceBar == 4) {retSpaceBarKey = SPACEBAR_5;}

		return retSpaceBarKey;
	}

	private static int getMin(int[] inputArray)
	{ 
	    int minValue = inputArray[0]; 
	    for(int i=1;i<inputArray.length;i++)
	    { 
	      if(inputArray[i] < minValue)
	      { 
	        minValue = inputArray[i]; 
	      } 
	    } 
	    return minValue; 
	  } 
	
	private int distanceBetweenTwoKeys_QWERTY(Key key1, Key key2)

	{
	    int distance = 0;
	    int rowOfKey1 = 0;
	    int indexOfKey1 = 0;
	    int rowOfKey2 = 0;
	    int indexOfKey2 = 0;
	    
	    Key[] rowOne = new Key[13];
		Key[] rowTwo = new Key[14];
	    Key[] rowThree = new Key[12];
	    Key[] rowFour = new Key[12];
	    Key[] spaceBarsArray = new Key[5];
	 
	    rowOne[0] = BACKTICK;
	    rowOne[1] = ONE;
	    rowOne[2] = TWO;
	    rowOne[3] = THREE;
	    rowOne[4] = FOUR;
	    rowOne[5] = FIVE;
	    rowOne[6] = SIX;
	    rowOne[7] = SEVEN;
	    rowOne[8] = EIGHT;
	    rowOne[9] = NINE;
	    rowOne[10] = ZERO;
	    rowOne[11] = MINUS;
	    rowOne[12] = EQUALS;
	    
	    rowTwo[0] = TAB;
	    rowTwo[1] = Q;
	    rowTwo[2] = W;
	    rowTwo[3] = E;
	    rowTwo[4] = R;
	    rowTwo[5] = T;
	    rowTwo[6] = Y;
	    rowTwo[7] = U;
	    rowTwo[8] = I;
	    rowTwo[9] = O;
	    rowTwo[10] = P;
	    rowTwo[11] = LEFT_BRACKET;
	    rowTwo[12] = RIGHT_BRACKET;
	    rowTwo[13] = BACKSLASH;
	
	    rowThree[0] = A;
	    rowThree[1] = S;
	    rowThree[2] = D;
	    rowThree[3] = F;
	    rowThree[4] = G;
	    rowThree[5] = H;
	    rowThree[6] = J;
	    rowThree[7] = K;
	    rowThree[8] = L;
	    rowThree[9] = SEMICOLON;
	    rowThree[10] = TICK;
	    rowThree[11] = RETURN;
	    
	    rowFour[0] = SHIFT_1;
	    rowFour[1] = Z;
	    rowFour[2] = X;
	    rowFour[3] = C;
	    rowFour[4] = V;
	    rowFour[5] = B;
	    rowFour[6] = N;
	    rowFour[7] = M;
	    rowFour[8] = COMMA;
	    rowFour[9] = PERIOD;
	    rowFour[10] = FORESLASH;
	    rowFour[11] = SHIFT_2;
	
	    spaceBarsArray[0] = SPACEBAR_1;
	    spaceBarsArray[1] = SPACEBAR_2;
	    spaceBarsArray[2] = SPACEBAR_3;
	    spaceBarsArray[3] = SPACEBAR_4;
	    spaceBarsArray[4] = SPACEBAR_5;

	    // Finding index and row of each key
	    for(int i = 0; i < rowOne.length; i++)
	    {
	        if(rowOne[i] == key1)
	        {
	          rowOfKey1 = 1;
	          indexOfKey1 = i;
	        }
	
	        if(rowOne[i] == key2)
	        {
	          rowOfKey2 = 1;
	          indexOfKey2 = i;
	        }
	    }
	    
	    for(int i = 0; i < rowTwo.length; i++)
	    {
	        if(rowTwo[i] == key1)
	        {
	          rowOfKey1 = 2;
	          indexOfKey1 = i;
	        }
	
	        if(rowTwo[i] == key2)
	        {
	          rowOfKey2 = 2;
	          indexOfKey2 = i;
	        }
	    }
	    
	    for(int i = 0; i < rowThree.length; i++)
	    {
	        if(rowThree[i] == key1)
	        {
	          rowOfKey1 = 3;
	          indexOfKey1 = i;
	        }
	
	        if(rowThree[i] == key2)
	        {
	          rowOfKey2 = 3;
	          indexOfKey2 = i;
	        }
	    }
	    
	    for(int i = 0; i < rowFour.length; i++)
	    {
	        if(rowFour[i] == key1)
	        {
	          rowOfKey1 = 4;
	          indexOfKey1 = i;
	        }
	
	        if(rowFour[i] == key2)
	        {
	          rowOfKey2 = 4;
	          indexOfKey2 = i;
	        }
	    }
	    
	    for(int i = 0; i < spaceBarsArray.length; i++)
	    {
	        if(spaceBarsArray[i] == key1)
	        {
	          rowOfKey1 = 5;
	          indexOfKey1 = i;
	        }
	
	        if(spaceBarsArray[i] == key2)
	        {
	          rowOfKey2 = 5;
	          indexOfKey2 = i;
	        }
	    }
	    
	    // Declaring absolute value of row difference as rowDifference
	    int rowDifference = rowOfKey1 - rowOfKey2;
	    if(rowDifference < 0)
	    {
	      rowDifference = rowDifference * -1;
	    }
	    
	    if(indexOfKey1 == indexOfKey2) // if indices are the same
	    {
	      if(rowDifference == 0)
	      {
	        distance = 0;
	      }
	
	      else if(rowDifference == 1)
	      {
	        if((rowOfKey1 == 1 && rowOfKey2 == 2) || (rowOfKey1 == 2 && rowOfKey2 == 1))
	        {
	          distance = 1;
	        }// end of row 1 -> 2
	        
	        if((rowOfKey1 == 2 && rowOfKey2 == 3) || (rowOfKey1 == 3 && rowOfKey2 == 2))
	        {
	          if(indexOfKey1 == 12)
	          {
	            distance = 1;
	          }
	          else
	          {
	            distance = 2;
	          }
	        } // end of row 2 -> 3
	
	        if((rowOfKey1 == 3 && rowOfKey2 == 4) || (rowOfKey1 == 4 && rowOfKey2 == 3))
	        {
	          distance = 1;
	        } // end of row 3 -> 4
	
	        if((rowOfKey1 == 4 && rowOfKey2 == 5) || (rowOfKey1 == 5 && rowOfKey2 == 4))
	        {
	          distance = 4;
	        }// end of row 4 -> 5
	      } // end of row difference == 1
	        
	      else if(rowDifference == 2)
	      {
	        if((rowOfKey1 == 1 && rowOfKey2 == 3) || (rowOfKey1 == 3 && rowOfKey2 == 1))
	        {
	          distance = 3;
	        } // end of row 1 -> 3
	
	        if((rowOfKey1 == 2 && rowOfKey2 == 4) || (rowOfKey1 == 4 && rowOfKey2 == 2))
	        {
	          if(indexOfKey1 == 0)
	          {
	            distance = 3;
	          }
	          else
	          {
	            distance = 2;
	          }       
	        } // end of row 2 -> 4
	
	        if((rowOfKey1 == 5 && rowOfKey2 == 3) || (rowOfKey1 == 3 && rowOfKey2 == 5))
	        {
	          distance = 4;
	        } // end of row 3 -> 5
	      }
	        
	      else if(rowDifference == 3)
	      {
	        if((rowOfKey1 == 1 && rowOfKey2 == 4) || (rowOfKey1 == 4 && rowOfKey2 == 1))
	        {
	          distance = 3;
	        } // end of row 1 -> 4
	
	        if((rowOfKey1 == 2 && rowOfKey2 == 5) || (rowOfKey1 == 2 && rowOfKey2 == 5))
	        {
	          if(indexOfKey1 == 0)
	          {
	            distance = 4;
	          }
	
	          else
	          {
	            distance = 7;
	          }
	        } // end of row 2 -> 5
	      }
	        
	      else if(rowDifference == 4)
	      {
	        distance = 7;
	      } // end of row difference == 4
	    } // end of equal indices
	
	    else if(indexOfKey1 != indexOfKey2) // if indices are NOT the same
	    {
	      if(rowDifference == 0)
	      {
	        distance = (indexOfKey2 - indexOfKey1);
	        if(distance < 0)
	        {
	          distance = distance * -1;
	        }
	      } // end of row difference == 0
	      if(rowDifference == 1)
	      {
	        if(rowOfKey1 == 2 && rowOfKey2 == 1) // key1 is second row, key2 is top row;
	        {
	          if(indexOfKey2 == indexOfKey1 + 1)
	          {
	            distance = 1;
	          }
	
	          else 
	          {
	            if(indexOfKey2 < indexOfKey1)
	            {
	              distance = (indexOfKey1 - indexOfKey2);
	              if(distance < 0) 
	              {
	                distance = distance * -1;
	              }
	              distance = distance + 1;
	            }
	            else
	            {
	              distance = (indexOfKey2 - indexOfKey1);
	              if(distance < 0)
	              {
	                distance = distance * -1;
	              }
	            }
	          }
	        } // end of row 2 -> 1
	  
	        else if(rowOfKey1 == 1 && rowOfKey2 == 2) 
	        {
	            if(indexOfKey1 < indexOfKey2)
	            {
	              distance = (indexOfKey2 - indexOfKey1) + 1;
	            }
	            else
	            {
	              distance = (indexOfKey1 - indexOfKey2);
	            }
	        } // end of row 1 -> 2
	
	        else if(rowOfKey1 == 2 && rowOfKey2 == 3)
	        {
	          if(indexOfKey1 == 0)
	          {
	            distance = indexOfKey2 + 2;
	          }
	
	          else
	          {
	           if(indexOfKey1 > indexOfKey2)
	            {
	              if((indexOfKey1 - indexOfKey2 == 1) || indexOfKey1 - indexOfKey2 == 2)
	              {
	                distance = 1;
	              }
	              else
	              {
	            	  distance = (indexOfKey1 - indexOfKey2) - 1;
	              }
	            }
	            else if(indexOfKey1 < indexOfKey2)
	            {
	              distance = (indexOfKey2 - indexOfKey1) + 2;
	            }
	          }
	        } // end of row 2 -> 3
	
	        else if(rowOfKey1 == 3 && rowOfKey2 == 2)
	        {
	          if(indexOfKey1 > indexOfKey2)
	          {
	            distance = (indexOfKey1 - indexOfKey2) + 2;
	          }
	
	          if(indexOfKey2 > indexOfKey1)
	          {
	            if((indexOfKey2 - indexOfKey1 == 1) || (indexOfKey2 - indexOfKey1 == 2))
	            {
	              distance = 1;
	            }
	            else
	            {
	              distance = ((indexOfKey2 - indexOfKey1) - 1);
	            }
	          }
	        } // end of row 3 -> 2
	        
	        else if(rowOfKey1 == 4 && rowOfKey2 == 3) 
	        {
	         if(indexOfKey1 > indexOfKey2)
	          {
	            if(indexOfKey1 - indexOfKey2 == 1)
	            {
	              distance = 1;
	            }
	
	            else
	            {
	              distance = indexOfKey1 - indexOfKey2;
	            }
	          }
	
	          if(indexOfKey2 > indexOfKey1)
	          {
	            distance = (indexOfKey2 - indexOfKey1) + 1;
	          }
	        } // end of 4 -> 3
	
	        else if(rowOfKey1 == 3 && rowOfKey2 == 4) 
	        {
	          if(indexOfKey2 > indexOfKey1)
	          {
	            if(indexOfKey2 - indexOfKey1 == 1)
	            {
	              distance = 1;
	            }
	
	            else
	            {
	              distance = indexOfKey2 - indexOfKey1;
	            }
	          } 
	
	           if(indexOfKey1 > indexOfKey2)
	            {
	            	distance = (indexOfKey1 - indexOfKey2) + 1;
	            	
	            }
	          }
	        } // end of row 3 -> 4
	
	        else if(rowOfKey1 == 4 && rowOfKey2 == 5) 
	        {
	          if(indexOfKey2 > indexOfKey1)
	          {
	            distance = (indexOfKey2 - indexOfKey1) + 4;
	          }
	
	          if(indexOfKey1 > indexOfKey2)
	          {
	            if((indexOfKey1 - indexOfKey2 == 1) || (indexOfKey1 - indexOfKey2 == 2))
	            {
	              distance = 2;
	            }
	
	            else
	            {
	              distance = (indexOfKey1 - indexOfKey2) - 2;
	            }
	          }
	        } // end of row 4 -> 5
	
	        else if(rowOfKey1 == 5 && rowOfKey2 == 4) 
	        {
	          if(indexOfKey1 > indexOfKey2)
	          {
	            distance = (indexOfKey1 - indexOfKey2) + 4;
	          }
	
	          if(indexOfKey2 > indexOfKey1)
	          {
	            if(indexOfKey2 - indexOfKey1 == 1)
	            {
	              distance = (indexOfKey2 - indexOfKey1) + 2;
	            }
	              
	            else if(indexOfKey2 - indexOfKey1 == 2)
	            {
	              distance = 2;
	            }
	
	            else
	            {
	              distance = (indexOfKey2 - indexOfKey1) - 2;
	            }
	          }
	        } // end of row 5 -> 4
	      } // end of row difference == 1
	      
	      if(rowDifference == 2)
	      {
	        if(rowOfKey1 == 1 && rowOfKey2 == 3)
	        {
	          if(indexOfKey2 > indexOfKey1)
	          {
	            if(indexOfKey2 - indexOfKey1 == 1)
	            {
	              distance = 4;
	            }
	
	            else
	            {
	              distance = (indexOfKey2 - indexOfKey1) + 3;
	            }
	          }
	
	          if(indexOfKey1 > indexOfKey2)
	          {
	            if((indexOfKey1 - indexOfKey2 == 1) || (indexOfKey1 - indexOfKey2 == 2) || (indexOfKey1 - indexOfKey2 == 3))
	            {
	              distance = 2;
	            }
	            else
	            {
	              distance = (indexOfKey1 - indexOfKey2) - 1;
	            }
	          }
	        } // end of row 1 -> 3
	
	        if(rowOfKey1 == 3 && rowOfKey2 == 1)
	        {
	          if(indexOfKey2 > indexOfKey1)
	          {
	            if((indexOfKey2 - indexOfKey1 == 1) || (indexOfKey2 - indexOfKey1 == 2))
	            {
	              distance = 2;
	            }
	
	            else
	            {
	              distance = (indexOfKey2 - indexOfKey1) - 1;
	            }
	          }
	
	          if(indexOfKey1 > indexOfKey2)
	          {
	            if(indexOfKey1 - indexOfKey2 == 1)
	            {
	              distance = 4;
	            }
	
	            else
	            {
	              distance = (indexOfKey1 - indexOfKey2) + 3;
	            }
	          }
	        } // end of row 3 -> 1
	
	        if(rowOfKey1 == 2 && rowOfKey2 == 4)
	        {
	          if(indexOfKey2 > indexOfKey1)
	          {
	            distance = (indexOfKey2 - indexOfKey1) + 2;
	          }
	
	          if(indexOfKey1 > indexOfKey2)
	          {
	            if(indexOfKey1 - indexOfKey2 == 1)
	            {
	              distance = 2;
	            }
	
	            else
	            {
	              distance = indexOfKey1 - indexOfKey2;
	            }
	          }
	        } // end of row 2 -> 4
	        
	        if(rowOfKey1 == 4 && rowOfKey2 == 2)
	        {
	          if(indexOfKey2 > indexOfKey1)
	          {
	            if(indexOfKey2 - indexOfKey1 == 1)
	            {
	              distance = 2;
	            }
	
	            else
	            {
	              distance = indexOfKey2 - indexOfKey1;
	            }
	          }
	
	          if(indexOfKey1 > indexOfKey2)
	          {
	            if(indexOfKey1 - indexOfKey2 == 1)
	            {
	              distance = 3;
	            }
	
	            else
	            {
	              distance = (indexOfKey1 - indexOfKey2) + 2;
	            }
	          }
	        } // end of row 4 -> 2
	
	        if(rowOfKey1 == 3 && rowOfKey2 == 5)
	        {
	          if(indexOfKey2 > indexOfKey1)
	          {
	            distance = (indexOfKey2 - indexOfKey1) + 4;
	          }
	
	          if(indexOfKey1 > indexOfKey2)
	          {
	            if(indexOfKey1 - indexOfKey2 == 1)
	            {
	              distance = 3;
	            }
	            if(indexOfKey1 - indexOfKey2 == 2)
	            {
	              distance = 2;
	            }
	
	            else
	            {
	              distance = (indexOfKey1 - indexOfKey2) - 1;
	            }
	          }
	        } // end of row 3 -> 5
	
	        if(rowOfKey1 == 5 && rowOfKey2 == 3) 
	        {
	          if(indexOfKey2 > indexOfKey1)
	          {
	            if(indexOfKey2 - indexOfKey1 == 1)
	            {
	              distance = 3;
	            }
	            if(indexOfKey2 - indexOfKey1 == 2)
	            {
	              distance = 2;
	            }
	            else
	            {
	              distance = (indexOfKey2 - indexOfKey1) - 1;
	            }
	          }
	
	          if(indexOfKey1 > indexOfKey2)
	          {
	            if(indexOfKey1 - indexOfKey2 == 1)
	            {
	              distance = 5;
	            }
	            else
	            {
	              distance = (indexOfKey1 - indexOfKey2) + 4;
	            }
	          }
	        } // end of row 5 -> 3
	      } // end of row difference == 2
	      
	      if(rowDifference == 3) 
	      {
	        if(rowOfKey1 == 1 && rowOfKey2 == 4) 
	        {
	          if(indexOfKey2 > indexOfKey1)
	          {
	            distance = (indexOfKey2 - indexOfKey1) + 3;
	          }
	
	          if(indexOfKey1 > indexOfKey2)
	          {
	            if((indexOfKey1 - indexOfKey2 == 1) || (indexOfKey1 - indexOfKey2 == 2))
	            {
	              distance = 3;
	            }
	            else
	            {
	              distance = indexOfKey1 - indexOfKey2;
	            }
	          }
	        } // end of row 1 -> 4
	
	        if(rowOfKey1 == 4 && rowOfKey2 == 1) 
	        {
	          if(indexOfKey2 < indexOfKey1)
	          {
	            distance = (indexOfKey1 - indexOfKey2) + 3;
	          }
	
	          if(indexOfKey1 < indexOfKey2)
	          {
	            if((indexOfKey2 - indexOfKey1 == 1) || (indexOfKey2 - indexOfKey1 == 2))
	            {
	              distance = 3;
	            }
	            else
	            {
	              distance = indexOfKey2 - indexOfKey1;
	            }
	          }
	        } // end of row 4 -> 1
	
	        if(rowOfKey1 == 2 && rowOfKey2 == 5) // key1 top, going to key2, bottom
	        {
	          if(indexOfKey2 > indexOfKey1)
	          {
	            distance = (indexOfKey2 - indexOfKey1) + 6;
	          }
	
	          if(indexOfKey1 > indexOfKey2)
	          {
	            if(indexOfKey1 - indexOfKey2 == 1)
	            {
	              distance = 5;
	            }
	            if(indexOfKey1 - indexOfKey2 == 2)
	            {
	              distance = 4;
	            }
	            else
	            {
	              distance = (indexOfKey1 - indexOfKey2) - 2;
	            }
	          }
	        } // end of row 2 -> 5
	
	        if(rowOfKey1 == 5 && rowOfKey2 == 2) 
	        {
	          if(indexOfKey2 < indexOfKey1)
	          {
	            distance = (indexOfKey2 - indexOfKey1) + 6;
	          }
	
	          if(indexOfKey1 < indexOfKey2)
	          {
	            if(indexOfKey2 - indexOfKey1 == 1)
	            {
	              distance = 5;
	            }
	            if(indexOfKey2 - indexOfKey1 == 2)
	            {
	              distance = 4;
	            }
	            else
	            {
	              distance = (indexOfKey2 - indexOfKey1) - 2;
	            }
	          }
	        } // end of row 5 -> 2
	      } // end of row difference == 3
	      
	      if(rowDifference == 4)
	      {
	        if(rowOfKey1 == 1 && rowOfKey2 == 5)
	        {
	          if(indexOfKey2 > indexOfKey1)
	          {
	            distance = (indexOfKey2 - indexOfKey1) + 7;
	          }
	          if(indexOfKey1 > indexOfKey2)
	          {
	            if(indexOfKey1 - indexOfKey2 == 1)
	            {
	              distance = 6;
	            }
	
	            if(indexOfKey1 - indexOfKey2 == 2)
	            {
	              distance = 5;
	            }
	
	            else
	            {
	              distance = (indexOfKey1 - indexOfKey2) -2;
	            }
	          }
	        } // end of row 1 -> 5
	
	        if(rowOfKey1 == 5 && rowOfKey2 == 1) // key1 top, going to key2, bottom
	        {
	          if(indexOfKey2 < indexOfKey1)
	          {
	            distance = (indexOfKey1 - indexOfKey2) + 7;
	          }
	          if(indexOfKey1 < indexOfKey2)
	          {
	            if(indexOfKey2 - indexOfKey1 == 1)
	            {
	              distance = 6;
	            }
	
	            if(indexOfKey2 - indexOfKey1 == 2)
	            {
	              distance = 5;
	            }
	
	            else
	            {
	              distance = (indexOfKey2 - indexOfKey1) -2;
	            }
	          }
	        } // end of row 5 -> 1
	       } // end of row difference == 4
	      return distance;
	      } // end of indices NOT the same
    
	private int distanceBetweenTwoKeys_DVORAK(Key key1, Key key2)
	{
	    int distance = 0;
	    int rowOfKey1 = 0;
	    int indexOfKey1 = 0;
	    int rowOfKey2 = 0;
	    int indexOfKey2 = 0;
	    
	    Key[] rowOne = new Key[13];
		Key[] rowTwo = new Key[14];
	    Key[] rowThree = new Key[12];
	    Key[] rowFour = new Key[12];
	    Key[] spaceBarsArray = new Key[5];
	 
	    rowOne[0] = BACKTICK;
	    rowOne[1] = ONE;
	    rowOne[2] = TWO;
	    rowOne[3] = THREE;
	    rowOne[4] = FOUR;
	    rowOne[5] = FIVE;
	    rowOne[6] = SIX;
	    rowOne[7] = SEVEN;
	    rowOne[8] = EIGHT;
	    rowOne[9] = NINE;
	    rowOne[10] = ZERO;
	    rowOne[11] = LEFT_BRACKET;
	    rowOne[12] = RIGHT_BRACKET;
	    
	    rowTwo[0] = TAB;
	    rowTwo[1] = TICK;
	    rowTwo[2] = COMMA;
	    rowTwo[3] = PERIOD;
	    rowTwo[4] = P;
	    rowTwo[5] = Y;
	    rowTwo[6] = F;
	    rowTwo[7] = G;
	    rowTwo[8] = C;
	    rowTwo[9] = R;
	    rowTwo[10] = L;
	    rowTwo[11] = FORESLASH;
	    rowTwo[12] = EQUALS;
	    rowTwo[13] = BACKSLASH;
	
	    rowThree[0] = A;
	    rowThree[1] = O;
	    rowThree[2] = E;
	    rowThree[3] = U;
	    rowThree[4] = I;
	    rowThree[5] = D;
	    rowThree[6] = H;
	    rowThree[7] = T;
	    rowThree[8] = N;
	    rowThree[9] = S;
	    rowThree[10] = MINUS;
	    rowThree[11] = RETURN;
	    
	    rowFour[0] = SHIFT_1;
	    rowFour[1] = SEMICOLON;
	    rowFour[2] = Q;
	    rowFour[3] = J;
	    rowFour[4] = K;
	    rowFour[5] = X;
	    rowFour[6] = B;
	    rowFour[7] = M;
	    rowFour[8] = W;
	    rowFour[9] = V;
	    rowFour[10] = Z;
	    rowFour[11] = SHIFT_2;
	
	    spaceBarsArray[0] = SPACEBAR_1;
	    spaceBarsArray[1] = SPACEBAR_2;
	    spaceBarsArray[2] = SPACEBAR_3;
	    spaceBarsArray[3] = SPACEBAR_4;
	    spaceBarsArray[4] = SPACEBAR_5;
	
	 // Finding index and row of each key
	    for(int i = 0; i < rowOne.length; i++)
	    {
	        if(rowOne[i] == key1)
	        {
	          rowOfKey1 = 1;
	          indexOfKey1 = i;
	        }
	
	        if(rowOne[i] == key2)
	        {
	          rowOfKey2 = 1;
	          indexOfKey2 = i;
	        }
	    }
	    
	    for(int i = 0; i < rowTwo.length; i++)
	    {
	        if(rowTwo[i] == key1)
	        {
	          rowOfKey1 = 2;
	          indexOfKey1 = i;
	        }
	
	        if(rowTwo[i] == key2)
	        {
	          rowOfKey2 = 2;
	          indexOfKey2 = i;
	        }
	    }
	    
	    for(int i = 0; i < rowThree.length; i++)
	    {
	        if(rowThree[i] == key1)
	        {
	          rowOfKey1 = 3;
	          indexOfKey1 = i;
	        }
	
	        if(rowThree[i] == key2)
	        {
	          rowOfKey2 = 3;
	          indexOfKey2 = i;
	        }
	    }
	    
	    for(int i = 0; i < rowFour.length; i++)
	    {
	        if(rowFour[i] == key1)
	        {
	          rowOfKey1 = 4;
	          indexOfKey1 = i;
	        }
	
	        if(rowFour[i] == key2)
	        {
	          rowOfKey2 = 4;
	          indexOfKey2 = i;
	        }
	    }
	    
	    for(int i = 0; i < spaceBarsArray.length; i++)
	    {
	        if(spaceBarsArray[i] == key1)
	        {
	          rowOfKey1 = 5;
	          indexOfKey1 = i;
	        }
	
	        if(spaceBarsArray[i] == key2)
	        {
	          rowOfKey2 = 5;
	          indexOfKey2 = i;
	        }
	    }
	    
	    // Declaring absolute value of row difference as rowDifference
	    int rowDifference = rowOfKey1 - rowOfKey2;
	    if(rowDifference < 0)
	    {
	      rowDifference = rowDifference * -1;
	    }
	    
	    if(indexOfKey1 == indexOfKey2) // if indices are the same
	    {
	      if(rowDifference == 0)
	      {
	        distance = 0;
	      }
	
	      else if(rowDifference == 1)
	      {
	        if((rowOfKey1 == 1 && rowOfKey2 == 2) || (rowOfKey1 == 2 && rowOfKey2 == 1))
	        {
	          distance = 1;
	        }// end of row 1 -> 2
	        
	        if((rowOfKey1 == 2 && rowOfKey2 == 3) || (rowOfKey1 == 3 && rowOfKey2 == 2))
	        {
	          if(indexOfKey1 == 12)
	          {
	            distance = 1;
	          }
	          else
	          {
	            distance = 2;
	          }
	        } // end of row 2 -> 3
	
	        if((rowOfKey1 == 3 && rowOfKey2 == 4) || (rowOfKey1 == 4 && rowOfKey2 == 3))
	        {
	          distance = 1;
	        } // end of row 3 -> 4
	
	        if((rowOfKey1 == 4 && rowOfKey2 == 5) || (rowOfKey1 == 5 && rowOfKey2 == 4))
	        {
	          distance = 4;
	        }// end of row 4 -> 5
	      } // end of row difference == 1
	        
	      else if(rowDifference == 2)
	      {
	        if((rowOfKey1 == 1 && rowOfKey2 == 3) || (rowOfKey1 == 3 && rowOfKey2 == 1))
	        {
	          distance = 3;
	        } // end of row 1 -> 3
	
	        if((rowOfKey1 == 2 && rowOfKey2 == 4) || (rowOfKey1 == 4 && rowOfKey2 == 2))
	        {
	          if(indexOfKey1 == 0)
	          {
	            distance = 3;
	          }
	          else
	          {
	            distance = 2;
	          }       
	        } // end of row 2 -> 4
	
	        if((rowOfKey1 == 5 && rowOfKey2 == 3) || (rowOfKey1 == 3 && rowOfKey2 == 5))
	        {
	          distance = 4;
	        } // end of row 3 -> 5
	      }
	        
	      else if(rowDifference == 3)
	      {
	        if((rowOfKey1 == 1 && rowOfKey2 == 4) || (rowOfKey1 == 4 && rowOfKey2 == 1))
	        {
	          distance = 3;
	        } // end of row 1 -> 4
	
	        if((rowOfKey1 == 2 && rowOfKey2 == 5) || (rowOfKey1 == 2 && rowOfKey2 == 5))
	        {
	          if(indexOfKey1 == 0)
	          {
	            distance = 4;
	          }
	
	          else
	          {
	            distance = 7;
	          }
	        } // end of row 2 -> 5
	      }
	        
	      else if(rowDifference == 4)
	      {
	        distance = 7;
	      } // end of row difference == 4
	    } // end of equal indices
	
	    else if(indexOfKey1 != indexOfKey2) // if indices are NOT the same
	    {
	      if(rowDifference == 0)
	      {
	        distance = (indexOfKey2 - indexOfKey1);
	        if(distance < 0)
	        {
	          distance = distance * -1;
	        }
	      } // end of row difference == 0
	      if(rowDifference == 1)
	      {
	        if(rowOfKey1 == 2 && rowOfKey2 == 1) // key1 is second row, key2 is top row;
	        {
	          if(indexOfKey2 == indexOfKey1 + 1)
	          {
	            distance = 1;
	          }
	
	          else 
	          {
	            if(indexOfKey2 < indexOfKey1)
	            {
	              distance = (indexOfKey1 - indexOfKey2);
	              if(distance < 0) 
	              {
	                distance = distance * -1;
	              }
	              distance = distance + 1;
	            }
	            else
	            {
	              distance = (indexOfKey2 - indexOfKey1);
	              if(distance < 0)
	              {
	                distance = distance * -1;
	              }
	            }
	          }
	        } // end of row 2 -> 1
	  
	        else if(rowOfKey1 == 1 && rowOfKey2 == 2) 
	        {
	            if(indexOfKey1 < indexOfKey2)
	            {
	              distance = (indexOfKey2 - indexOfKey1) + 1;
	            }
	            else
	            {
	              distance = (indexOfKey1 - indexOfKey2);
	            }
	        } // end of row 1 -> 2
	
	        else if(rowOfKey1 == 2 && rowOfKey2 == 3)
	        {
	          if(indexOfKey1 == 0)
	          {
	            distance = indexOfKey2 + 2;
	          }
	
	          else
	          {
	           if(indexOfKey1 > indexOfKey2)
	            {
	              if((indexOfKey1 - indexOfKey2 == 1) || indexOfKey1 - indexOfKey2 == 2)
	              {
	                distance = 1;
	              }
	              else
	              {
	            	  distance = (indexOfKey1 - indexOfKey2) - 1;
	              }
	            }
	            else if(indexOfKey1 < indexOfKey2)
	            {
	              distance = (indexOfKey2 - indexOfKey1) + 2;
	            }
	          }
	        } // end of row 2 -> 3
	
	        else if(rowOfKey1 == 3 && rowOfKey2 == 2)
	        {
	          if(indexOfKey1 > indexOfKey2)
	          {
	            distance = (indexOfKey1 - indexOfKey2) + 2;
	          }
	
	          if(indexOfKey2 > indexOfKey1)
	          {
	            if((indexOfKey2 - indexOfKey1 == 1) || (indexOfKey2 - indexOfKey1 == 2))
	            {
	              distance = 1;
	            }
	            else
	            {
	              distance = ((indexOfKey2 - indexOfKey1) - 1);
	            }
	          }
	        } // end of row 3 -> 2
	        
	        else if(rowOfKey1 == 4 && rowOfKey2 == 3) 
	        {
	         if(indexOfKey1 > indexOfKey2)
	          {
	            if(indexOfKey1 - indexOfKey2 == 1)
	            {
	              distance = 1;
	            }
	
	            else
	            {
	              distance = indexOfKey1 - indexOfKey2;
	            }
	          }
	
	          if(indexOfKey2 > indexOfKey1)
	          {
	            distance = (indexOfKey2 - indexOfKey1) + 1;
	          }
	        } // end of 4 -> 3
	
	        else if(rowOfKey1 == 3 && rowOfKey2 == 4) 
	        {
	          if(indexOfKey2 > indexOfKey1)
	          {
	            if(indexOfKey2 - indexOfKey1 == 1)
	            {
	              distance = 1;
	            }
	
	            else
	            {
	              distance = indexOfKey2 - indexOfKey1;
	            }
	          } 
	
	           if(indexOfKey1 > indexOfKey2)
	            {
	            	distance = (indexOfKey1 - indexOfKey2) + 1;
	            	
	            }
	          }
	        } // end of row 3 -> 4
	
	        else if(rowOfKey1 == 4 && rowOfKey2 == 5) 
	        {
	          if(indexOfKey2 > indexOfKey1)
	          {
	            distance = (indexOfKey2 - indexOfKey1) + 4;
	          }
	
	          if(indexOfKey1 > indexOfKey2)
	          {
	            if((indexOfKey1 - indexOfKey2 == 1) || (indexOfKey1 - indexOfKey2 == 2))
	            {
	              distance = 2;
	            }
	
	            else
	            {
	              distance = (indexOfKey1 - indexOfKey2) - 2;
	            }
	          }
	        } // end of row 4 -> 5
	
	        else if(rowOfKey1 == 5 && rowOfKey2 == 4) 
	        {
	          if(indexOfKey1 > indexOfKey2)
	          {
	            distance = (indexOfKey1 - indexOfKey2) + 4;
	          }
	
	          if(indexOfKey2 > indexOfKey1)
	          {
	            if(indexOfKey2 - indexOfKey1 == 1)
	            {
	              distance = (indexOfKey2 - indexOfKey1) + 2;
	            }
	              
	            else if(indexOfKey2 - indexOfKey1 == 2)
	            {
	              distance = 2;
	            }
	
	            else
	            {
	              distance = (indexOfKey2 - indexOfKey1) - 2;
	            }
	          }
	        } // end of row 5 -> 4
	      } // end of row difference == 1
	      
	      if(rowDifference == 2)
	      {
	        if(rowOfKey1 == 1 && rowOfKey2 == 3)
	        {
	          if(indexOfKey2 > indexOfKey1)
	          {
	            if(indexOfKey2 - indexOfKey1 == 1)
	            {
	              distance = 4;
	            }
	
	            else
	            {
	              distance = (indexOfKey2 - indexOfKey1) + 3;
	            }
	          }
	
	          if(indexOfKey1 > indexOfKey2)
	          {
	            if((indexOfKey1 - indexOfKey2 == 1) || (indexOfKey1 - indexOfKey2 == 2) || (indexOfKey1 - indexOfKey2 == 3))
	            {
	              distance = 2;
	            }
	            else
	            {
	              distance = (indexOfKey1 - indexOfKey2) - 1;
	            }
	          }
	        } // end of row 1 -> 3
	
	        if(rowOfKey1 == 3 && rowOfKey2 == 1)
	        {
	          if(indexOfKey2 > indexOfKey1)
	          {
	            if((indexOfKey2 - indexOfKey1 == 1) || (indexOfKey2 - indexOfKey1 == 2))
	            {
	              distance = 2;
	            }
	
	            else
	            {
	              distance = (indexOfKey2 - indexOfKey1) - 1;
	            }
	          }
	
	          if(indexOfKey1 > indexOfKey2)
	          {
	            if(indexOfKey1 - indexOfKey2 == 1)
	            {
	              distance = 4;
	            }
	
	            else
	            {
	              distance = (indexOfKey1 - indexOfKey2) + 3;
	            }
	          }
	        } // end of row 3 -> 1
	
	        if(rowOfKey1 == 2 && rowOfKey2 == 4)
	        {
	          if(indexOfKey2 > indexOfKey1)
	          {
	            distance = (indexOfKey2 - indexOfKey1) + 2;
	          }
	
	          if(indexOfKey1 > indexOfKey2)
	          {
	            if(indexOfKey1 - indexOfKey2 == 1)
	            {
	              distance = 2;
	            }
	
	            else
	            {
	              distance = indexOfKey1 - indexOfKey2;
	            }
	          }
	        } // end of row 2 -> 4
	        
	        if(rowOfKey1 == 4 && rowOfKey2 == 2)
	        {
	          if(indexOfKey2 > indexOfKey1)
	          {
	            if(indexOfKey2 - indexOfKey1 == 1)
	            {
	              distance = 2;
	            }
	
	            else
	            {
	              distance = indexOfKey2 - indexOfKey1;
	            }
	          }
	
	          if(indexOfKey1 > indexOfKey2)
	          {
	            if(indexOfKey1 - indexOfKey2 == 1)
	            {
	              distance = 3;
	            }
	
	            else
	            {
	              distance = (indexOfKey1 - indexOfKey2) + 2;
	            }
	          }
	        } // end of row 4 -> 2
	
	        if(rowOfKey1 == 3 && rowOfKey2 == 5)
	        {
	          if(indexOfKey2 > indexOfKey1)
	          {
	            distance = (indexOfKey2 - indexOfKey1) + 4;
	          }
	
	          if(indexOfKey1 > indexOfKey2)
	          {
	            if(indexOfKey1 - indexOfKey2 == 1)
	            {
	              distance = 3;
	            }
	            if(indexOfKey1 - indexOfKey2 == 2)
	            {
	              distance = 2;
	            }
	
	            else
	            {
	              distance = (indexOfKey1 - indexOfKey2) - 1;
	            }
	          }
	        } // end of row 3 -> 5
	
	        if(rowOfKey1 == 5 && rowOfKey2 == 3) 
	        {
	          if(indexOfKey2 > indexOfKey1)
	          {
	            if(indexOfKey2 - indexOfKey1 == 1)
	            {
	              distance = 3;
	            }
	            if(indexOfKey2 - indexOfKey1 == 2)
	            {
	              distance = 2;
	            }
	            else
	            {
	              distance = (indexOfKey2 - indexOfKey1) - 1;
	            }
	          }
	
	          if(indexOfKey1 > indexOfKey2)
	          {
	            if(indexOfKey1 - indexOfKey2 == 1)
	            {
	              distance = 5;
	            }
	            else
	            {
	              distance = (indexOfKey1 - indexOfKey2) + 4;
	            }
	          }
	        } // end of row 5 -> 3
	      } // end of row difference == 2
	      
	      if(rowDifference == 3) 
	      {
	        if(rowOfKey1 == 1 && rowOfKey2 == 4) 
	        {
	          if(indexOfKey2 > indexOfKey1)
	          {
	            distance = (indexOfKey2 - indexOfKey1) + 3;
	          }
	
	          if(indexOfKey1 > indexOfKey2)
	          {
	            if((indexOfKey1 - indexOfKey2 == 1) || (indexOfKey1 - indexOfKey2 == 2))
	            {
	              distance = 3;
	            }
	            else
	            {
	              distance = indexOfKey1 - indexOfKey2;
	            }
	          }
	        } // end of row 1 -> 4
	
	        if(rowOfKey1 == 4 && rowOfKey2 == 1) 
	        {
	          if(indexOfKey2 < indexOfKey1)
	          {
	            distance = (indexOfKey1 - indexOfKey2) + 3;
	          }
	
	          if(indexOfKey1 < indexOfKey2)
	          {
	            if((indexOfKey2 - indexOfKey1 == 1) || (indexOfKey2 - indexOfKey1 == 2))
	            {
	              distance = 3;
	            }
	            else
	            {
	              distance = indexOfKey2 - indexOfKey1;
	            }
	          }
	        } // end of row 4 -> 1
	
	        if(rowOfKey1 == 2 && rowOfKey2 == 5) // key1 top, going to key2, bottom
	        {
	          if(indexOfKey2 > indexOfKey1)
	          {
	            distance = (indexOfKey2 - indexOfKey1) + 6;
	          }
	
	          if(indexOfKey1 > indexOfKey2)
	          {
	            if(indexOfKey1 - indexOfKey2 == 1)
	            {
	              distance = 5;
	            }
	            if(indexOfKey1 - indexOfKey2 == 2)
	            {
	              distance = 4;
	            }
	            else
	            {
	              distance = (indexOfKey1 - indexOfKey2) - 2;
	            }
	          }
	        } // end of row 2 -> 5
	
	        if(rowOfKey1 == 5 && rowOfKey2 == 2) 
	        {
	          if(indexOfKey2 < indexOfKey1)
	          {
	            distance = (indexOfKey2 - indexOfKey1) + 6;
	          }
	
	          if(indexOfKey1 < indexOfKey2)
	          {
	            if(indexOfKey2 - indexOfKey1 == 1)
	            {
	              distance = 5;
	            }
	            if(indexOfKey2 - indexOfKey1 == 2)
	            {
	              distance = 4;
	            }
	            else
	            {
	              distance = (indexOfKey2 - indexOfKey1) - 2;
	            }
	          }
	        } // end of row 5 -> 2
	      } // end of row difference == 3
	      
	      if(rowDifference == 4)
	      {
	        if(rowOfKey1 == 1 && rowOfKey2 == 5)
	        {
	          if(indexOfKey2 > indexOfKey1)
	          {
	            distance = (indexOfKey2 - indexOfKey1) + 7;
	          }
	          if(indexOfKey1 > indexOfKey2)
	          {
	            if(indexOfKey1 - indexOfKey2 == 1)
	            {
	              distance = 6;
	            }
	
	            if(indexOfKey1 - indexOfKey2 == 2)
	            {
	              distance = 5;
	            }
	
	            else
	            {
	              distance = (indexOfKey1 - indexOfKey2) -2;
	            }
	          }
	        } // end of row 1 -> 5
	
	        if(rowOfKey1 == 5 && rowOfKey2 == 1) // key1 top, going to key2, bottom
	        {
	          if(indexOfKey2 < indexOfKey1)
	          {
	            distance = (indexOfKey1 - indexOfKey2) + 7;
	          }
	          if(indexOfKey1 < indexOfKey2)
	          {
	            if(indexOfKey2 - indexOfKey1 == 1)
	            {
	              distance = 6;
	            }
	
	            if(indexOfKey2 - indexOfKey1 == 2)
	            {
	              distance = 5;
	            }
	
	            else
	            {
	              distance = (indexOfKey2 - indexOfKey1) -2;
	            }
	          }
	        } // end of row 5 -> 1
	       } // end of row difference == 4
	      return distance;
	      } // end of indices NOT the same
	
	private int distanceBetweenTwoKeys_COLEMAK(Key key1, Key key2)
	{
	    int distance = 0;
	    int rowOfKey1 = 0;
	    int indexOfKey1 = 0;
	    int rowOfKey2 = 0;
	    int indexOfKey2 = 0;
	    
	    Key[] rowOne = new Key[13];
		Key[] rowTwo = new Key[14];
	    Key[] rowThree = new Key[12];
	    Key[] rowFour = new Key[12];
	    Key[] spaceBarsArray = new Key[5];
	 
	    rowOne[0] = BACKTICK;
	    rowOne[1] = ONE;
	    rowOne[2] = TWO;
	    rowOne[3] = THREE;
	    rowOne[4] = FOUR;
	    rowOne[5] = FIVE;
	    rowOne[6] = SIX;
	    rowOne[7] = SEVEN;
	    rowOne[8] = EIGHT;
	    rowOne[9] = NINE;
	    rowOne[10] = ZERO;
	    rowOne[11] = MINUS;
	    rowOne[12] = EQUALS;
	    
	    rowTwo[0] = TAB;
	    rowTwo[1] = Q;
	    rowTwo[2] = W;
	    rowTwo[3] = F;
	    rowTwo[4] = P;
	    rowTwo[5] = G;
	    rowTwo[6] = J;
	    rowTwo[7] = L;
	    rowTwo[8] = U;
	    rowTwo[9] = Y;
	    rowTwo[10] = SEMICOLON;
	    rowTwo[11] = LEFT_BRACKET;
	    rowTwo[12] = RIGHT_BRACKET;
	    rowTwo[13] = BACKSLASH;
	
	    rowThree[0] = A;
	    rowThree[1] = R;
	    rowThree[2] = S;
	    rowThree[3] = T;
	    rowThree[4] = D;
	    rowThree[5] = H;
	    rowThree[6] = N;
	    rowThree[7] = E;
	    rowThree[8] = I;
	    rowThree[9] = O;
	    rowThree[10] = TICK;
	    rowThree[11] = RETURN;
	    
	    rowFour[0] = SHIFT_1;
	    rowFour[1] = Z;
	    rowFour[2] = X;
	    rowFour[3] = C;
	    rowFour[4] = V;
	    rowFour[5] = B;
	    rowFour[6] = K;
	    rowFour[7] = M;
	    rowFour[8] = COMMA;
	    rowFour[9] = PERIOD;
	    rowFour[10] = FORESLASH;
	    rowFour[11] = SHIFT_2;
	
	    spaceBarsArray[0] = SPACEBAR_1;
	    spaceBarsArray[1] = SPACEBAR_2;
	    spaceBarsArray[2] = SPACEBAR_3;
	    spaceBarsArray[3] = SPACEBAR_4;
	    spaceBarsArray[4] = SPACEBAR_5;
	
	 // Finding index and row of each key
	    for(int i = 0; i < rowOne.length; i++)
	    {
	        if(rowOne[i] == key1)
	        {
	          rowOfKey1 = 1;
	          indexOfKey1 = i;
	        }
	
	        if(rowOne[i] == key2)
	        {
	          rowOfKey2 = 1;
	          indexOfKey2 = i;
	        }
	    }
	    
	    for(int i = 0; i < rowTwo.length; i++)
	    {
	        if(rowTwo[i] == key1)
	        {
	          rowOfKey1 = 2;
	          indexOfKey1 = i;
	        }
	
	        if(rowTwo[i] == key2)
	        {
	          rowOfKey2 = 2;
	          indexOfKey2 = i;
	        }
	    }
	    
	    for(int i = 0; i < rowThree.length; i++)
	    {
	        if(rowThree[i] == key1)
	        {
	          rowOfKey1 = 3;
	          indexOfKey1 = i;
	        }
	
	        if(rowThree[i] == key2)
	        {
	          rowOfKey2 = 3;
	          indexOfKey2 = i;
	        }
	    }
	    
	    for(int i = 0; i < rowFour.length; i++)
	    {
	        if(rowFour[i] == key1)
	        {
	          rowOfKey1 = 4;
	          indexOfKey1 = i;
	        }
	
	        if(rowFour[i] == key2)
	        {
	          rowOfKey2 = 4;
	          indexOfKey2 = i;
	        }
	    }
	    
	    for(int i = 0; i < spaceBarsArray.length; i++)
	    {
	        if(spaceBarsArray[i] == key1)
	        {
	          rowOfKey1 = 5;
	          indexOfKey1 = i;
	        }
	
	        if(spaceBarsArray[i] == key2)
	        {
	          rowOfKey2 = 5;
	          indexOfKey2 = i;
	        }
	    }
	    
	    // Declaring absolute value of row difference as rowDifference
	    int rowDifference = rowOfKey1 - rowOfKey2;
	    if(rowDifference < 0)
	    {
	      rowDifference = rowDifference * -1;
	    }
	    
	    if(indexOfKey1 == indexOfKey2) // if indices are the same
	    {
	      if(rowDifference == 0)
	      {
	        distance = 0;
	      }
	
	      else if(rowDifference == 1)
	      {
	        if((rowOfKey1 == 1 && rowOfKey2 == 2) || (rowOfKey1 == 2 && rowOfKey2 == 1))
	        {
	          distance = 1;
	        }// end of row 1 -> 2
	        
	        if((rowOfKey1 == 2 && rowOfKey2 == 3) || (rowOfKey1 == 3 && rowOfKey2 == 2))
	        {
	          if(indexOfKey1 == 12)
	          {
	            distance = 1;
	          }
	          else
	          {
	            distance = 2;
	          }
	        } // end of row 2 -> 3
	
	        if((rowOfKey1 == 3 && rowOfKey2 == 4) || (rowOfKey1 == 4 && rowOfKey2 == 3))
	        {
	          distance = 1;
	        } // end of row 3 -> 4
	
	        if((rowOfKey1 == 4 && rowOfKey2 == 5) || (rowOfKey1 == 5 && rowOfKey2 == 4))
	        {
	          distance = 4;
	        }// end of row 4 -> 5
	      } // end of row difference == 1
	        
	      else if(rowDifference == 2)
	      {
	        if((rowOfKey1 == 1 && rowOfKey2 == 3) || (rowOfKey1 == 3 && rowOfKey2 == 1))
	        {
	          distance = 3;
	        } // end of row 1 -> 3
	
	        if((rowOfKey1 == 2 && rowOfKey2 == 4) || (rowOfKey1 == 4 && rowOfKey2 == 2))
	        {
	          if(indexOfKey1 == 0)
	          {
	            distance = 3;
	          }
	          else
	          {
	            distance = 2;
	          }       
	        } // end of row 2 -> 4
	
	        if((rowOfKey1 == 5 && rowOfKey2 == 3) || (rowOfKey1 == 3 && rowOfKey2 == 5))
	        {
	          distance = 4;
	        } // end of row 3 -> 5
	      }
	        
	      else if(rowDifference == 3)
	      {
	        if((rowOfKey1 == 1 && rowOfKey2 == 4) || (rowOfKey1 == 4 && rowOfKey2 == 1))
	        {
	          distance = 3;
	        } // end of row 1 -> 4
	
	        if((rowOfKey1 == 2 && rowOfKey2 == 5) || (rowOfKey1 == 2 && rowOfKey2 == 5))
	        {
	          if(indexOfKey1 == 0)
	          {
	            distance = 4;
	          }
	
	          else
	          {
	            distance = 7;
	          }
	        } // end of row 2 -> 5
	      }
	        
	      else if(rowDifference == 4)
	      {
	        distance = 7;
	      } // end of row difference == 4
	    } // end of equal indices
	
	    else if(indexOfKey1 != indexOfKey2) // if indices are NOT the same
	    {
	      if(rowDifference == 0)
	      {
	        distance = (indexOfKey2 - indexOfKey1);
	        if(distance < 0)
	        {
	          distance = distance * -1;
	        }
	      } // end of row difference == 0
	      if(rowDifference == 1)
	      {
	        if(rowOfKey1 == 2 && rowOfKey2 == 1) // key1 is second row, key2 is top row;
	        {
	          if(indexOfKey2 == indexOfKey1 + 1)
	          {
	            distance = 1;
	          }
	
	          else 
	          {
	            if(indexOfKey2 < indexOfKey1)
	            {
	              distance = (indexOfKey1 - indexOfKey2);
	              if(distance < 0) 
	              {
	                distance = distance * -1;
	              }
	              distance = distance + 1;
	            }
	            else
	            {
	              distance = (indexOfKey2 - indexOfKey1);
	              if(distance < 0)
	              {
	                distance = distance * -1;
	              }
	            }
	          }
	        } // end of row 2 -> 1
	  
	        else if(rowOfKey1 == 1 && rowOfKey2 == 2) 
	        {
	            if(indexOfKey1 < indexOfKey2)
	            {
	              distance = (indexOfKey2 - indexOfKey1) + 1;
	            }
	            else
	            {
	              distance = (indexOfKey1 - indexOfKey2);
	            }
	        } // end of row 1 -> 2
	
	        else if(rowOfKey1 == 2 && rowOfKey2 == 3)
	        {
	          if(indexOfKey1 == 0)
	          {
	            distance = indexOfKey2 + 2;
	          }
	
	          else
	          {
	           if(indexOfKey1 > indexOfKey2)
	            {
	              if((indexOfKey1 - indexOfKey2 == 1) || indexOfKey1 - indexOfKey2 == 2)
	              {
	                distance = 1;
	              }
	              else
	              {
	            	  distance = (indexOfKey1 - indexOfKey2) - 1;
	              }
	            }
	            else if(indexOfKey1 < indexOfKey2)
	            {
	              distance = (indexOfKey2 - indexOfKey1) + 2;
	            }
	          }
	        } // end of row 2 -> 3
	
	        else if(rowOfKey1 == 3 && rowOfKey2 == 2)
	        {
	          if(indexOfKey1 > indexOfKey2)
	          {
	            distance = (indexOfKey1 - indexOfKey2) + 2;
	          }
	
	          if(indexOfKey2 > indexOfKey1)
	          {
	            if((indexOfKey2 - indexOfKey1 == 1) || (indexOfKey2 - indexOfKey1 == 2))
	            {
	              distance = 1;
	            }
	            else
	            {
	              distance = ((indexOfKey2 - indexOfKey1) - 1);
	            }
	          }
	        } // end of row 3 -> 2
	        
	        else if(rowOfKey1 == 4 && rowOfKey2 == 3) 
	        {
	         if(indexOfKey1 > indexOfKey2)
	          {
	            if(indexOfKey1 - indexOfKey2 == 1)
	            {
	              distance = 1;
	            }
	
	            else
	            {
	              distance = indexOfKey1 - indexOfKey2;
	            }
	          }
	
	          if(indexOfKey2 > indexOfKey1)
	          {
	            distance = (indexOfKey2 - indexOfKey1) + 1;
	          }
	        } // end of 4 -> 3
	
	        else if(rowOfKey1 == 3 && rowOfKey2 == 4) 
	        {
	          if(indexOfKey2 > indexOfKey1)
	          {
	            if(indexOfKey2 - indexOfKey1 == 1)
	            {
	              distance = 1;
	            }
	
	            else
	            {
	              distance = indexOfKey2 - indexOfKey1;
	            }
	          } 
	
	           if(indexOfKey1 > indexOfKey2)
	            {
	            	distance = (indexOfKey1 - indexOfKey2) + 1;
	            	
	            }
	          }
	        } // end of row 3 -> 4
	
	        else if(rowOfKey1 == 4 && rowOfKey2 == 5) 
	        {
	          if(indexOfKey2 > indexOfKey1)
	          {
	            distance = (indexOfKey2 - indexOfKey1) + 4;
	          }
	
	          if(indexOfKey1 > indexOfKey2)
	          {
	            if((indexOfKey1 - indexOfKey2 == 1) || (indexOfKey1 - indexOfKey2 == 2))
	            {
	              distance = 2;
	            }
	
	            else
	            {
	              distance = (indexOfKey1 - indexOfKey2) - 2;
	            }
	          }
	        } // end of row 4 -> 5
	
	        else if(rowOfKey1 == 5 && rowOfKey2 == 4) 
	        {
	          if(indexOfKey1 > indexOfKey2)
	          {
	            distance = (indexOfKey1 - indexOfKey2) + 4;
	          }
	
	          if(indexOfKey2 > indexOfKey1)
	          {
	            if(indexOfKey2 - indexOfKey1 == 1)
	            {
	              distance = (indexOfKey2 - indexOfKey1) + 2;
	            }
	              
	            else if(indexOfKey2 - indexOfKey1 == 2)
	            {
	              distance = 2;
	            }
	
	            else
	            {
	              distance = (indexOfKey2 - indexOfKey1) - 2;
	            }
	          }
	        } // end of row 5 -> 4
	      } // end of row difference == 1
	      
	      if(rowDifference == 2)
	      {
	        if(rowOfKey1 == 1 && rowOfKey2 == 3)
	        {
	          if(indexOfKey2 > indexOfKey1)
	          {
	            if(indexOfKey2 - indexOfKey1 == 1)
	            {
	              distance = 4;
	            }
	
	            else
	            {
	              distance = (indexOfKey2 - indexOfKey1) + 3;
	            }
	          }
	
	          if(indexOfKey1 > indexOfKey2)
	          {
	            if((indexOfKey1 - indexOfKey2 == 1) || (indexOfKey1 - indexOfKey2 == 2) || (indexOfKey1 - indexOfKey2 == 3))
	            {
	              distance = 2;
	            }
	            else
	            {
	              distance = (indexOfKey1 - indexOfKey2) - 1;
	            }
	          }
	        } // end of row 1 -> 3
	
	        if(rowOfKey1 == 3 && rowOfKey2 == 1)
	        {
	          if(indexOfKey2 > indexOfKey1)
	          {
	            if((indexOfKey2 - indexOfKey1 == 1) || (indexOfKey2 - indexOfKey1 == 2))
	            {
	              distance = 2;
	            }
	
	            else
	            {
	              distance = (indexOfKey2 - indexOfKey1) - 1;
	            }
	          }
	
	          if(indexOfKey1 > indexOfKey2)
	          {
	            if(indexOfKey1 - indexOfKey2 == 1)
	            {
	              distance = 4;
	            }
	
	            else
	            {
	              distance = (indexOfKey1 - indexOfKey2) + 3;
	            }
	          }
	        } // end of row 3 -> 1
	
	        if(rowOfKey1 == 2 && rowOfKey2 == 4)
	        {
	          if(indexOfKey2 > indexOfKey1)
	          {
	            distance = (indexOfKey2 - indexOfKey1) + 2;
	          }
	
	          if(indexOfKey1 > indexOfKey2)
	          {
	            if(indexOfKey1 - indexOfKey2 == 1)
	            {
	              distance = 2;
	            }
	
	            else
	            {
	              distance = indexOfKey1 - indexOfKey2;
	            }
	          }
	        } // end of row 2 -> 4
	        
	        if(rowOfKey1 == 4 && rowOfKey2 == 2)
	        {
	          if(indexOfKey2 > indexOfKey1)
	          {
	            if(indexOfKey2 - indexOfKey1 == 1)
	            {
	              distance = 2;
	            }
	
	            else
	            {
	              distance = indexOfKey2 - indexOfKey1;
	            }
	          }
	
	          if(indexOfKey1 > indexOfKey2)
	          {
	            if(indexOfKey1 - indexOfKey2 == 1)
	            {
	              distance = 3;
	            }
	
	            else
	            {
	              distance = (indexOfKey1 - indexOfKey2) + 2;
	            }
	          }
	        } // end of row 4 -> 2
	
	        if(rowOfKey1 == 3 && rowOfKey2 == 5)
	        {
	          if(indexOfKey2 > indexOfKey1)
	          {
	            distance = (indexOfKey2 - indexOfKey1) + 4;
	          }
	
	          if(indexOfKey1 > indexOfKey2)
	          {
	            if(indexOfKey1 - indexOfKey2 == 1)
	            {
	              distance = 3;
	            }
	            if(indexOfKey1 - indexOfKey2 == 2)
	            {
	              distance = 2;
	            }
	
	            else
	            {
	              distance = (indexOfKey1 - indexOfKey2) - 1;
	            }
	          }
	        } // end of row 3 -> 5
	
	        if(rowOfKey1 == 5 && rowOfKey2 == 3) 
	        {
	          if(indexOfKey2 > indexOfKey1)
	          {
	            if(indexOfKey2 - indexOfKey1 == 1)
	            {
	              distance = 3;
	            }
	            if(indexOfKey2 - indexOfKey1 == 2)
	            {
	              distance = 2;
	            }
	            else
	            {
	              distance = (indexOfKey2 - indexOfKey1) - 1;
	            }
	          }
	
	          if(indexOfKey1 > indexOfKey2)
	          {
	            if(indexOfKey1 - indexOfKey2 == 1)
	            {
	              distance = 5;
	            }
	            else
	            {
	              distance = (indexOfKey1 - indexOfKey2) + 4;
	            }
	          }
	        } // end of row 5 -> 3
	      } // end of row difference == 2
	      
	      if(rowDifference == 3) 
	      {
	        if(rowOfKey1 == 1 && rowOfKey2 == 4) 
	        {
	          if(indexOfKey2 > indexOfKey1)
	          {
	            distance = (indexOfKey2 - indexOfKey1) + 3;
	          }
	
	          if(indexOfKey1 > indexOfKey2)
	          {
	            if((indexOfKey1 - indexOfKey2 == 1) || (indexOfKey1 - indexOfKey2 == 2))
	            {
	              distance = 3;
	            }
	            else
	            {
	              distance = indexOfKey1 - indexOfKey2;
	            }
	          }
	        } // end of row 1 -> 4
	
	        if(rowOfKey1 == 4 && rowOfKey2 == 1) 
	        {
	          if(indexOfKey2 < indexOfKey1)
	          {
	            distance = (indexOfKey1 - indexOfKey2) + 3;
	          }
	
	          if(indexOfKey1 < indexOfKey2)
	          {
	            if((indexOfKey2 - indexOfKey1 == 1) || (indexOfKey2 - indexOfKey1 == 2))
	            {
	              distance = 3;
	            }
	            else
	            {
	              distance = indexOfKey2 - indexOfKey1;
	            }
	          }
	        } // end of row 4 -> 1
	
	        if(rowOfKey1 == 2 && rowOfKey2 == 5) // key1 top, going to key2, bottom
	        {
	          if(indexOfKey2 > indexOfKey1)
	          {
	            distance = (indexOfKey2 - indexOfKey1) + 6;
	          }
	
	          if(indexOfKey1 > indexOfKey2)
	          {
	            if(indexOfKey1 - indexOfKey2 == 1)
	            {
	              distance = 5;
	            }
	            if(indexOfKey1 - indexOfKey2 == 2)
	            {
	              distance = 4;
	            }
	            else
	            {
	              distance = (indexOfKey1 - indexOfKey2) - 2;
	            }
	          }
	        } // end of row 2 -> 5
	
	        if(rowOfKey1 == 5 && rowOfKey2 == 2) 
	        {
	          if(indexOfKey2 < indexOfKey1)
	          {
	            distance = (indexOfKey2 - indexOfKey1) + 6;
	          }
	
	          if(indexOfKey1 < indexOfKey2)
	          {
	            if(indexOfKey2 - indexOfKey1 == 1)
	            {
	              distance = 5;
	            }
	            if(indexOfKey2 - indexOfKey1 == 2)
	            {
	              distance = 4;
	            }
	            else
	            {
	              distance = (indexOfKey2 - indexOfKey1) - 2;
	            }
	          }
	        } // end of row 5 -> 2
	      } // end of row difference == 3
	      
	      if(rowDifference == 4)
	      {
	        if(rowOfKey1 == 1 && rowOfKey2 == 5)
	        {
	          if(indexOfKey2 > indexOfKey1)
	          {
	            distance = (indexOfKey2 - indexOfKey1) + 7;
	          }
	          if(indexOfKey1 > indexOfKey2)
	          {
	            if(indexOfKey1 - indexOfKey2 == 1)
	            {
	              distance = 6;
	            }
	
	            if(indexOfKey1 - indexOfKey2 == 2)
	            {
	              distance = 5;
	            }
	
	            else
	            {
	              distance = (indexOfKey1 - indexOfKey2) -2;
	            }
	          }
	        } // end of row 1 -> 5
	
	        if(rowOfKey1 == 5 && rowOfKey2 == 1) // key1 top, going to key2, bottom
	        {
	          if(indexOfKey2 < indexOfKey1)
	          {
	            distance = (indexOfKey1 - indexOfKey2) + 7;
	          }
	          if(indexOfKey1 < indexOfKey2)
	          {
	            if(indexOfKey2 - indexOfKey1 == 1)
	            {
	              distance = 6;
	            }
	
	            if(indexOfKey2 - indexOfKey1 == 2)
	            {
	              distance = 5;
	            }
	
	            else
	            {
	              distance = (indexOfKey2 - indexOfKey1) -2;
	            }
	          }
	        } // end of row 5 -> 1
	       } // end of row difference == 4
	      return distance;
	      } // end of indices NOT the same
	
	private int distanceBetweenTwoKeys_ROT13(Key key1, Key key2)

	{
	    int distance = 0;
	    int rowOfKey1 = 0;
	    int indexOfKey1 = 0;
	    int rowOfKey2 = 0;
	    int indexOfKey2 = 0;
	    
	    Key[] rowOne = new Key[13];
		Key[] rowTwo = new Key[14];
	    Key[] rowThree = new Key[12];
	    Key[] rowFour = new Key[12];
	    Key[] spaceBarsArray = new Key[5];
	 
	    rowOne[0] = BACKTICK;
	    rowOne[1] = ONE;
	    rowOne[2] = TWO;
	    rowOne[3] = THREE;
	    rowOne[4] = FOUR;
	    rowOne[5] = FIVE;
	    rowOne[6] = SIX;
	    rowOne[7] = SEVEN;
	    rowOne[8] = EIGHT;
	    rowOne[9] = NINE;
	    rowOne[10] = ZERO;
	    rowOne[11] = MINUS;
	    rowOne[12] = EQUALS;
	    
	    rowTwo[0] = TAB;
	    rowTwo[1] = D;
	    rowTwo[2] = J;
	    rowTwo[3] = R;
	    rowTwo[4] = E;
	    rowTwo[5] = G;
	    rowTwo[6] = L;
	    rowTwo[7] = H;
	    rowTwo[8] = V;
	    rowTwo[9] = B;
	    rowTwo[10] =C;
	    rowTwo[11] = LEFT_BRACKET;
	    rowTwo[12] = RIGHT_BRACKET;
	    rowTwo[13] = BACKSLASH;
	
	    rowThree[0] = N;
	    rowThree[1] = F;
	    rowThree[2] = Q;
	    rowThree[3] = S;
	    rowThree[4] = T;
	    rowThree[5] = U;
	    rowThree[6] = W;
	    rowThree[7] = X;
	    rowThree[8] = Y;
	    rowThree[9] = SEMICOLON;
	    rowThree[10] = TICK;
	    rowThree[11] = RETURN;
	    
	    rowFour[0] = SHIFT_1;
	    rowFour[1] = M;
	    rowFour[2] = K;
	    rowFour[3] = P;
	    rowFour[4] = I;
	    rowFour[5] = O;
	    rowFour[6] = A;
	    rowFour[7] = Z;
	    rowFour[8] = COMMA;
	    rowFour[9] = PERIOD;
	    rowFour[10] = FORESLASH;
	    rowFour[11] = SHIFT_2;
	
	    spaceBarsArray[0] = SPACEBAR_1;
	    spaceBarsArray[1] = SPACEBAR_2;
	    spaceBarsArray[2] = SPACEBAR_3;
	    spaceBarsArray[3] = SPACEBAR_4;
	    spaceBarsArray[4] = SPACEBAR_5;
	
	 // Finding index and row of each key
	    for(int i = 0; i < rowOne.length; i++)
	    {
	        if(rowOne[i] == key1)
	        {
	          rowOfKey1 = 1;
	          indexOfKey1 = i;
	        }
	
	        if(rowOne[i] == key2)
	        {
	          rowOfKey2 = 1;
	          indexOfKey2 = i;
	        }
	    }
	    
	    for(int i = 0; i < rowTwo.length; i++)
	    {
	        if(rowTwo[i] == key1)
	        {
	          rowOfKey1 = 2;
	          indexOfKey1 = i;
	        }
	
	        if(rowTwo[i] == key2)
	        {
	          rowOfKey2 = 2;
	          indexOfKey2 = i;
	        }
	    }
	    
	    for(int i = 0; i < rowThree.length; i++)
	    {
	        if(rowThree[i] == key1)
	        {
	          rowOfKey1 = 3;
	          indexOfKey1 = i;
	        }
	
	        if(rowThree[i] == key2)
	        {
	          rowOfKey2 = 3;
	          indexOfKey2 = i;
	        }
	    }
	    
	    for(int i = 0; i < rowFour.length; i++)
	    {
	        if(rowFour[i] == key1)
	        {
	          rowOfKey1 = 4;
	          indexOfKey1 = i;
	        }
	
	        if(rowFour[i] == key2)
	        {
	          rowOfKey2 = 4;
	          indexOfKey2 = i;
	        }
	    }
	    
	    for(int i = 0; i < spaceBarsArray.length; i++)
	    {
	        if(spaceBarsArray[i] == key1)
	        {
	          rowOfKey1 = 5;
	          indexOfKey1 = i;
	        }
	
	        if(spaceBarsArray[i] == key2)
	        {
	          rowOfKey2 = 5;
	          indexOfKey2 = i;
	        }
	    }
	    
	    // Declaring absolute value of row difference as rowDifference
	    int rowDifference = rowOfKey1 - rowOfKey2;
	    if(rowDifference < 0)
	    {
	      rowDifference = rowDifference * -1;
	    }
	    
	    if(indexOfKey1 == indexOfKey2) // if indices are the same
	    {
	      if(rowDifference == 0)
	      {
	        distance = 0;
	      }
	
	      else if(rowDifference == 1)
	      {
	        if((rowOfKey1 == 1 && rowOfKey2 == 2) || (rowOfKey1 == 2 && rowOfKey2 == 1))
	        {
	          distance = 1;
	        }// end of row 1 -> 2
	        
	        if((rowOfKey1 == 2 && rowOfKey2 == 3) || (rowOfKey1 == 3 && rowOfKey2 == 2))
	        {
	          if(indexOfKey1 == 12)
	          {
	            distance = 1;
	          }
	          else
	          {
	            distance = 2;
	          }
	        } // end of row 2 -> 3
	
	        if((rowOfKey1 == 3 && rowOfKey2 == 4) || (rowOfKey1 == 4 && rowOfKey2 == 3))
	        {
	          distance = 1;
	        } // end of row 3 -> 4
	
	        if((rowOfKey1 == 4 && rowOfKey2 == 5) || (rowOfKey1 == 5 && rowOfKey2 == 4))
	        {
	          distance = 4;
	        }// end of row 4 -> 5
	      } // end of row difference == 1
	        
	      else if(rowDifference == 2)
	      {
	        if((rowOfKey1 == 1 && rowOfKey2 == 3) || (rowOfKey1 == 3 && rowOfKey2 == 1))
	        {
	          distance = 3;
	        } // end of row 1 -> 3
	
	        if((rowOfKey1 == 2 && rowOfKey2 == 4) || (rowOfKey1 == 4 && rowOfKey2 == 2))
	        {
	          if(indexOfKey1 == 0)
	          {
	            distance = 3;
	          }
	          else
	          {
	            distance = 2;
	          }       
	        } // end of row 2 -> 4
	
	        if((rowOfKey1 == 5 && rowOfKey2 == 3) || (rowOfKey1 == 3 && rowOfKey2 == 5))
	        {
	          distance = 4;
	        } // end of row 3 -> 5
	      }
	        
	      else if(rowDifference == 3)
	      {
	        if((rowOfKey1 == 1 && rowOfKey2 == 4) || (rowOfKey1 == 4 && rowOfKey2 == 1))
	        {
	          distance = 3;
	        } // end of row 1 -> 4
	
	        if((rowOfKey1 == 2 && rowOfKey2 == 5) || (rowOfKey1 == 2 && rowOfKey2 == 5))
	        {
	          if(indexOfKey1 == 0)
	          {
	            distance = 4;
	          }
	
	          else
	          {
	            distance = 7;
	          }
	        } // end of row 2 -> 5
	      }
	        
	      else if(rowDifference == 4)
	      {
	        distance = 7;
	      } // end of row difference == 4
	    } // end of equal indices
	
	    else if(indexOfKey1 != indexOfKey2) // if indices are NOT the same
	    {
	      if(rowDifference == 0)
	      {
	        distance = (indexOfKey2 - indexOfKey1);
	        if(distance < 0)
	        {
	          distance = distance * -1;
	        }
	      } // end of row difference == 0
	      if(rowDifference == 1)
	      {
	        if(rowOfKey1 == 2 && rowOfKey2 == 1) // key1 is second row, key2 is top row;
	        {
	          if(indexOfKey2 == indexOfKey1 + 1)
	          {
	            distance = 1;
	          }
	
	          else 
	          {
	            if(indexOfKey2 < indexOfKey1)
	            {
	              distance = (indexOfKey1 - indexOfKey2);
	              if(distance < 0) 
	              {
	                distance = distance * -1;
	              }
	              distance = distance + 1;
	            }
	            else
	            {
	              distance = (indexOfKey2 - indexOfKey1);
	              if(distance < 0)
	              {
	                distance = distance * -1;
	              }
	            }
	          }
	        } // end of row 2 -> 1
	  
	        else if(rowOfKey1 == 1 && rowOfKey2 == 2) 
	        {
	            if(indexOfKey1 < indexOfKey2)
	            {
	              distance = (indexOfKey2 - indexOfKey1) + 1;
	            }
	            else
	            {
	              distance = (indexOfKey1 - indexOfKey2);
	            }
	        } // end of row 1 -> 2
	
	        else if(rowOfKey1 == 2 && rowOfKey2 == 3)
	        {
	          if(indexOfKey1 == 0)
	          {
	            distance = indexOfKey2 + 2;
	          }
	
	          else
	          {
	           if(indexOfKey1 > indexOfKey2)
	            {
	              if((indexOfKey1 - indexOfKey2 == 1) || indexOfKey1 - indexOfKey2 == 2)
	              {
	                distance = 1;
	              }
	              else
	              {
	            	  distance = (indexOfKey1 - indexOfKey2) - 1;
	              }
	            }
	            else if(indexOfKey1 < indexOfKey2)
	            {
	              distance = (indexOfKey2 - indexOfKey1) + 2;
	            }
	          }
	        } // end of row 2 -> 3
	
	        else if(rowOfKey1 == 3 && rowOfKey2 == 2)
	        {
	          if(indexOfKey1 > indexOfKey2)
	          {
	            distance = (indexOfKey1 - indexOfKey2) + 2;
	          }
	
	          if(indexOfKey2 > indexOfKey1)
	          {
	            if((indexOfKey2 - indexOfKey1 == 1) || (indexOfKey2 - indexOfKey1 == 2))
	            {
	              distance = 1;
	            }
	            else
	            {
	              distance = ((indexOfKey2 - indexOfKey1) - 1);
	            }
	          }
	        } // end of row 3 -> 2
	        
	        else if(rowOfKey1 == 4 && rowOfKey2 == 3) 
	        {
	         if(indexOfKey1 > indexOfKey2)
	          {
	            if(indexOfKey1 - indexOfKey2 == 1)
	            {
	              distance = 1;
	            }
	
	            else
	            {
	              distance = indexOfKey1 - indexOfKey2;
	            }
	          }
	
	          if(indexOfKey2 > indexOfKey1)
	          {
	            distance = (indexOfKey2 - indexOfKey1) + 1;
	          }
	        } // end of 4 -> 3
	
	        else if(rowOfKey1 == 3 && rowOfKey2 == 4) 
	        {
	          if(indexOfKey2 > indexOfKey1)
	          {
	            if(indexOfKey2 - indexOfKey1 == 1)
	            {
	              distance = 1;
	            }
	
	            else
	            {
	              distance = indexOfKey2 - indexOfKey1;
	            }
	          } 
	
	           if(indexOfKey1 > indexOfKey2)
	            {
	            	distance = (indexOfKey1 - indexOfKey2) + 1;
	            	
	            }
	          }
	        } // end of row 3 -> 4
	
	        else if(rowOfKey1 == 4 && rowOfKey2 == 5) 
	        {
	          if(indexOfKey2 > indexOfKey1)
	          {
	            distance = (indexOfKey2 - indexOfKey1) + 4;
	          }
	
	          if(indexOfKey1 > indexOfKey2)
	          {
	            if((indexOfKey1 - indexOfKey2 == 1) || (indexOfKey1 - indexOfKey2 == 2))
	            {
	              distance = 2;
	            }
	
	            else
	            {
	              distance = (indexOfKey1 - indexOfKey2) - 2;
	            }
	          }
	        } // end of row 4 -> 5
	
	        else if(rowOfKey1 == 5 && rowOfKey2 == 4) 
	        {
	          if(indexOfKey1 > indexOfKey2)
	          {
	            distance = (indexOfKey1 - indexOfKey2) + 4;
	          }
	
	          if(indexOfKey2 > indexOfKey1)
	          {
	            if(indexOfKey2 - indexOfKey1 == 1)
	            {
	              distance = (indexOfKey2 - indexOfKey1) + 2;
	            }
	              
	            else if(indexOfKey2 - indexOfKey1 == 2)
	            {
	              distance = 2;
	            }
	
	            else
	            {
	              distance = (indexOfKey2 - indexOfKey1) - 2;
	            }
	          }
	        } // end of row 5 -> 4
	      } // end of row difference == 1
	      
	      if(rowDifference == 2)
	      {
	        if(rowOfKey1 == 1 && rowOfKey2 == 3)
	        {
	          if(indexOfKey2 > indexOfKey1)
	          {
	            if(indexOfKey2 - indexOfKey1 == 1)
	            {
	              distance = 4;
	            }
	
	            else
	            {
	              distance = (indexOfKey2 - indexOfKey1) + 3;
	            }
	          }
	
	          if(indexOfKey1 > indexOfKey2)
	          {
	            if((indexOfKey1 - indexOfKey2 == 1) || (indexOfKey1 - indexOfKey2 == 2) || (indexOfKey1 - indexOfKey2 == 3))
	            {
	              distance = 2;
	            }
	            else
	            {
	              distance = (indexOfKey1 - indexOfKey2) - 1;
	            }
	          }
	        } // end of row 1 -> 3
	
	        if(rowOfKey1 == 3 && rowOfKey2 == 1)
	        {
	          if(indexOfKey2 > indexOfKey1)
	          {
	            if((indexOfKey2 - indexOfKey1 == 1) || (indexOfKey2 - indexOfKey1 == 2))
	            {
	              distance = 2;
	            }
	
	            else
	            {
	              distance = (indexOfKey2 - indexOfKey1) - 1;
	            }
	          }
	
	          if(indexOfKey1 > indexOfKey2)
	          {
	            if(indexOfKey1 - indexOfKey2 == 1)
	            {
	              distance = 4;
	            }
	
	            else
	            {
	              distance = (indexOfKey1 - indexOfKey2) + 3;
	            }
	          }
	        } // end of row 3 -> 1
	
	        if(rowOfKey1 == 2 && rowOfKey2 == 4)
	        {
	          if(indexOfKey2 > indexOfKey1)
	          {
	            distance = (indexOfKey2 - indexOfKey1) + 2;
	          }
	
	          if(indexOfKey1 > indexOfKey2)
	          {
	            if(indexOfKey1 - indexOfKey2 == 1)
	            {
	              distance = 2;
	            }
	
	            else
	            {
	              distance = indexOfKey1 - indexOfKey2;
	            }
	          }
	        } // end of row 2 -> 4
	        
	        if(rowOfKey1 == 4 && rowOfKey2 == 2)
	        {
	          if(indexOfKey2 > indexOfKey1)
	          {
	            if(indexOfKey2 - indexOfKey1 == 1)
	            {
	              distance = 2;
	            }
	
	            else
	            {
	              distance = indexOfKey2 - indexOfKey1;
	            }
	          }
	
	          if(indexOfKey1 > indexOfKey2)
	          {
	            if(indexOfKey1 - indexOfKey2 == 1)
	            {
	              distance = 3;
	            }
	
	            else
	            {
	              distance = (indexOfKey1 - indexOfKey2) + 2;
	            }
	          }
	        } // end of row 4 -> 2
	
	        if(rowOfKey1 == 3 && rowOfKey2 == 5)
	        {
	          if(indexOfKey2 > indexOfKey1)
	          {
	            distance = (indexOfKey2 - indexOfKey1) + 4;
	          }
	
	          if(indexOfKey1 > indexOfKey2)
	          {
	            if(indexOfKey1 - indexOfKey2 == 1)
	            {
	              distance = 3;
	            }
	            if(indexOfKey1 - indexOfKey2 == 2)
	            {
	              distance = 2;
	            }
	
	            else
	            {
	              distance = (indexOfKey1 - indexOfKey2) - 1;
	            }
	          }
	        } // end of row 3 -> 5
	
	        if(rowOfKey1 == 5 && rowOfKey2 == 3) 
	        {
	          if(indexOfKey2 > indexOfKey1)
	          {
	            if(indexOfKey2 - indexOfKey1 == 1)
	            {
	              distance = 3;
	            }
	            if(indexOfKey2 - indexOfKey1 == 2)
	            {
	              distance = 2;
	            }
	            else
	            {
	              distance = (indexOfKey2 - indexOfKey1) - 1;
	            }
	          }
	
	          if(indexOfKey1 > indexOfKey2)
	          {
	            if(indexOfKey1 - indexOfKey2 == 1)
	            {
	              distance = 5;
	            }
	            else
	            {
	              distance = (indexOfKey1 - indexOfKey2) + 4;
	            }
	          }
	        } // end of row 5 -> 3
	      } // end of row difference == 2
	      
	      if(rowDifference == 3) 
	      {
	        if(rowOfKey1 == 1 && rowOfKey2 == 4) 
	        {
	          if(indexOfKey2 > indexOfKey1)
	          {
	            distance = (indexOfKey2 - indexOfKey1) + 3;
	          }
	
	          if(indexOfKey1 > indexOfKey2)
	          {
	            if((indexOfKey1 - indexOfKey2 == 1) || (indexOfKey1 - indexOfKey2 == 2))
	            {
	              distance = 3;
	            }
	            else
	            {
	              distance = indexOfKey1 - indexOfKey2;
	            }
	          }
	        } // end of row 1 -> 4
	
	        if(rowOfKey1 == 4 && rowOfKey2 == 1) 
	        {
	          if(indexOfKey2 < indexOfKey1)
	          {
	            distance = (indexOfKey1 - indexOfKey2) + 3;
	          }
	
	          if(indexOfKey1 < indexOfKey2)
	          {
	            if((indexOfKey2 - indexOfKey1 == 1) || (indexOfKey2 - indexOfKey1 == 2))
	            {
	              distance = 3;
	            }
	            else
	            {
	              distance = indexOfKey2 - indexOfKey1;
	            }
	          }
	        } // end of row 4 -> 1
	
	        if(rowOfKey1 == 2 && rowOfKey2 == 5) // key1 top, going to key2, bottom
	        {
	          if(indexOfKey2 > indexOfKey1)
	          {
	            distance = (indexOfKey2 - indexOfKey1) + 6;
	          }
	
	          if(indexOfKey1 > indexOfKey2)
	          {
	            if(indexOfKey1 - indexOfKey2 == 1)
	            {
	              distance = 5;
	            }
	            if(indexOfKey1 - indexOfKey2 == 2)
	            {
	              distance = 4;
	            }
	            else
	            {
	              distance = (indexOfKey1 - indexOfKey2) - 2;
	            }
	          }
	        } // end of row 2 -> 5
	
	        if(rowOfKey1 == 5 && rowOfKey2 == 2) 
	        {
	          if(indexOfKey2 < indexOfKey1)
	          {
	            distance = (indexOfKey2 - indexOfKey1) + 6;
	          }
	
	          if(indexOfKey1 < indexOfKey2)
	          {
	            if(indexOfKey2 - indexOfKey1 == 1)
	            {
	              distance = 5;
	            }
	            if(indexOfKey2 - indexOfKey1 == 2)
	            {
	              distance = 4;
	            }
	            else
	            {
	              distance = (indexOfKey2 - indexOfKey1) - 2;
	            }
	          }
	        } // end of row 5 -> 2
	      } // end of row difference == 3
	      
	      if(rowDifference == 4)
	      {
	        if(rowOfKey1 == 1 && rowOfKey2 == 5)
	        {
	          if(indexOfKey2 > indexOfKey1)
	          {
	            distance = (indexOfKey2 - indexOfKey1) + 7;
	          }
	          if(indexOfKey1 > indexOfKey2)
	          {
	            if(indexOfKey1 - indexOfKey2 == 1)
	            {
	              distance = 6;
	            }
	
	            if(indexOfKey1 - indexOfKey2 == 2)
	            {
	              distance = 5;
	            }
	
	            else
	            {
	              distance = (indexOfKey1 - indexOfKey2) -2;
	            }
	          }
	        } // end of row 1 -> 5
	
	        if(rowOfKey1 == 5 && rowOfKey2 == 1) // key1 top, going to key2, bottom
	        {
	          if(indexOfKey2 < indexOfKey1)
	          {
	            distance = (indexOfKey1 - indexOfKey2) + 7;
	          }
	          if(indexOfKey1 < indexOfKey2)
	          {
	            if(indexOfKey2 - indexOfKey1 == 1)
	            {
	              distance = 6;
	            }
	
	            if(indexOfKey2 - indexOfKey1 == 2)
	            {
	              distance = 5;
	            }
	
	            else
	            {
	              distance = (indexOfKey2 - indexOfKey1) -2;
	            }
	          }
	        } // end of row 5 -> 1
	       } // end of row difference == 4
	      return distance;
	      } // end of indices NOT the same

}