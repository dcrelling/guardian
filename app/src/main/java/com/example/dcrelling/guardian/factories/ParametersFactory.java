package com.example.dcrelling.guardian.factories;

import java.util.HashMap;
import java.util.Map;

import com.example.dcrelling.guardian.services.GuardianService;

/**
 * Created by dcrelling on 8/21/16.
 */

public class ParametersFactory
{


  public Map<String, String> getParameters(GuardianService.ApiType type)
  {

    Map<String, String> params = buildDefault();

    switch (type)
    {
      case CONTENT:
        break;
      case SECTION:
        break;
    }
    return params;
  }


  private Map<String, String> buildDefault()
  {
    Map<String, String> params = new HashMap<>();
    params.put("api-key", GuardianService.API_KEY);
    params.put("format", "json");
    params.put("from-date", "2016-08-21");
    params.put("page", "1");
    params.put("page-size", "10");
    return params;
  }


}
