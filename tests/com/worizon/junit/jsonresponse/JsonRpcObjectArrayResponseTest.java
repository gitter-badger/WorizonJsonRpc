package com.worizon.junit.jsonresponse;

import static org.junit.Assert.*;

import org.junit.Test;

import com.worizon.jsonrpc.JsonRpcResponse;

public class JsonRpcObjectArrayResponseTest {
	
	class Foo{
		
		int dni;
		String name;
		String surname;
		float height;
		
		public Foo( int dni, String name, String surname, float height){
			
			this.dni = dni;
			this.name = name;
			this.surname = surname;
			this.height = height;
		}
		
		public boolean equals( Object obj ){
			Foo foo = (Foo)obj;
			return dni == foo.dni && name.equals(foo.name) && surname.equals(foo.surname) && height == foo.height;
		}
	}

	@Test
	public void testResult() throws Exception {
		
		String message = "{\"jsonrpc\": \"2.0\", \"result\": [{\"dni\":46579878,\"name\":\"Enric\",\"surname\":\"Cecilla\",\"height\":1.72}], \"id\": 77684}";		
		JsonRpcResponse<Foo[]> res = new JsonRpcResponse<Foo[]>(message, Foo[].class);		
				
		Foo expected = new Foo(46579878,"Enric","Cecilla",1.72f);			
		assertArrayEquals( new Foo[]{expected}, res.getResult());
		assertNull(res.getError());
		
		String message2 = "{\"jsonrpc\": \"2.0\", \"result\": [{\"dni\":46579878,\"name\":\"Enric\",\"surname\":\"Cecilla\",\"height\":1.72},null], \"id\": 77684}";		
		JsonRpcResponse<Foo[]> res2 = new JsonRpcResponse<Foo[]>(message2, Foo[].class);
		assertArrayEquals( new Foo[]{expected,null}, res2.getResult());
	}
	
	@Test
	public void testNull() throws Exception{
		
		String message = "{\"jsonrpc\": \"2.0\", \"result\": null, \"id\": 77684}";		
		JsonRpcResponse<Foo[]> res = new JsonRpcResponse<Foo[]>(message, Foo[].class);	
		
		assertNull(res.getResult());
		
	}
					
		
}
