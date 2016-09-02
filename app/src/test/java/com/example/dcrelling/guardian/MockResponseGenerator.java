package com.example.dcrelling.guardian;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;

import android.content.res.Resources;
import android.util.Log;

/**
 * Created by dcrelling on 9/1/16.
 */

public class MockResponseGenerator
{
  private static String TAG = MockResponseGenerator.class.getName();


  public String generate(String path)
  {
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path);
    Writer writer = new StringWriter();
    try
    {
      BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
      String line = reader.readLine();
      while (line != null)
      {
        writer.write(line);
        line = reader.readLine();
      }
    }
    catch (Exception e)
    {
      //Log.e(TAG, "Unhandled exception while using JSONResourceReader", e);
    }
    finally
    {
      try
      {
        inputStream.close();
      }
      catch (Exception e)
      {
        //Log.e(TAG, "Unhandled exception while using JSONResourceReader", e);
      }
    }

    return writer.toString();
  }
}
