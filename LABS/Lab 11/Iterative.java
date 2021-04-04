public class Iterative {

	public static BitList complement( BitList in ) {
		
		BitList outcome = new BitList();
		Iterator input = in.iterator();
		Iterator output = outcome.iterator();
		
		while(input.hasNext()) {

			int bit = input.next();
			
			if(bit == BitList.ONE) {
				output.add(BitList.ZERO);
			}

			else if(bit == BitList.ZERO) {
				output.add(BitList.ONE);
			}
			
		}

		return outcome;
	}

	public static BitList or( BitList a, BitList b ) {
		BitList outcome = new BitList();
		
		Iterator first = a.iterator();
		Iterator second = b.iterator();
		Iterator result = outcome.iterator();
		
		if(!first.hasNext()) {
            throw new IllegalArgumentException( "a is empty" );
		}
		if(!second.hasNext()) {
            throw new IllegalArgumentException( "b is empty" );
		}
		while(first.hasNext()) {
			if(!second.hasNext()) {
	             throw new IllegalArgumentException( "a and b are not of the same size" );
			}
			int firstBit = first.next();
			int secondBit = second.next();
			
			if(firstBit == BitList.ONE || secondBit == BitList.ONE) {
				result.add(BitList.ONE);
			}
			else {
				result.add(BitList.ZERO);
			}
		}
		if(second.hasNext()) {
            throw new IllegalArgumentException( "a and b are not of the same size" );
		}
		return outcome;
	}

	public static BitList and( BitList a, BitList b ) {

		BitList outcome = new BitList();
		Iterator first = a.iterator();
		Iterator second = b.iterator();

		if(!first.hasNext()) {
            throw new IllegalArgumentException( "a is empty" );
		}

		if(!second.hasNext()) {
            throw new IllegalArgumentException( "b is empty" );
		}

		Iterator result = outcome.iterator();
		
		while(first.hasNext()) {

			if(!second.hasNext()) {
				throw new IllegalArgumentException( "a and b are not of the same size" );
		   }

			int firstBit = first.next();
			int secondBit = second.next();
			
			if(firstBit == BitList.ZERO || secondBit == BitList.ZERO) {
				result.add(BitList.ZERO);
			}

			else {
				result.add(BitList.ONE);
			}
		}

		if(second.hasNext()) {
            throw new IllegalArgumentException( "a and b are not of the same size" );
		}

		return outcome;
	}

	public static BitList xor( BitList a, BitList b ) {

		BitList outcome = new BitList();
		Iterator first = a.iterator();
		Iterator second = b.iterator();

		if(!first.hasNext()) {
            throw new IllegalArgumentException( "a is empty" );
		}

		if(!second.hasNext()) {
            throw new IllegalArgumentException( "b is empty" );
		}

		Iterator result = outcome.iterator();
		
		while(first.hasNext()) {

			if(!second.hasNext()) {
				throw new IllegalArgumentException( "a and b are not of the same size" );
		   }

			int firstBit = first.next();
			int secondBit = second.next();
			
			if(firstBit == secondBit) {
				result.add(BitList.ZERO);
			}

			else {
				result.add(BitList.ONE);
			}

		}

		if(second.hasNext()) {
            throw new IllegalArgumentException( "a and b are not of the same size" );
		}

		return outcome;
	}
}
