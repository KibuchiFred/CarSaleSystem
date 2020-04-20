package com.grocery.demo;

import com.grocery.demo.Repository.DataCentre;
import com.grocery.demo.Service.CalculateSum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

@InjectMocks
    CalculateSum calculateSum;

@Mock
    DataCentre dataCentre;

@Test
    public void DataMockingTest(){
    when(dataCentre.retriveData()).thenReturn(new int[]{45,45,45});
    assertEquals(135, calculateSum.dataCalculator());
}

@Test
    public void OneElementMockingTest(){
    when(dataCentre.retriveData()).thenReturn(new int[]{345});
    assertEquals(345, calculateSum.dataCalculator());
}

@Test
    public void EmptyArrayMockingTest(){
    when(dataCentre.retriveData()).thenReturn(new int[]{});
    assertEquals(0, calculateSum.dataCalculator());
}

//verifying method calls

    List<String> result = mock(List.class);

@Test
    public void verifyMethodCalls(){
    String checkOne = result.get(0);
    String checkTwo = result.get(1);

    //verify that get method is being called
    verify(result).get(0);//verify with specific values
    verify(result, atLeastOnce()).get(anyInt());//verify with any argument matchers
    verify(result, times(1)).get(0);
    verify(result, atLeastOnce()).get(anyInt());
    verify(result, never()).get(2);

}

//capturing a value passed to a method call
    @Test
    public void captureSomeArgument(){
    result.add("input value");

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(result).add(captor.capture());

        assertEquals("input value", captor.getValue());
 }
 //capturing multiple values
    @Test
    public void multipleValuesCapture(){

    result.add("input stringOne");
    result.add("input stringTwo");

    ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
    verify(result, times(2)).add(captor.capture());

    List<String> allValues = captor.getAllValues();

    assertEquals("input stringOne", allValues.get(0));
    assertEquals("input stringTwo", allValues.get(1));
    }

}
