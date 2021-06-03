package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import src.Tic;

import org.junit.Test;
import org.junit.platform.console.shadow.picocli.CommandLine.Parameters;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


@RunWith(Parameterized.class)
public class TicTest{

    Tic obj = new Tic();

    public TicTest(int cols, int rows){


    }

    @Parameters
    public static List<Tic[]> data(){
        return null;
    }



    @Test
    public void boardTest(){

        String[][] result = {{"_|","_|", "_"}, {"_|","_|", "_"}};

        assertEquals(result, obj.emptyBoard(2,3));
    }
}