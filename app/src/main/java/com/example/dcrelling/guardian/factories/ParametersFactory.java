package com.example.dcrelling.guardian.factories;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import com.example.dcrelling.guardian.services.GuardianService;

/**
 * Created by dcrelling on 8/21/16.
 */

public class ParametersFactory
{

  private final static String API_KEY = "api-key";
  private final static String FORMAT = "format";
  private final static String RESPONSE_FORMAT = "json";
  private final static String FROM_DATE = "from-date";
  private final static String NUM_PAGES = "page";
  private final static int DEFAULT_NUM_PAGES = 1;
  private final static String PAGE_SIZE = "page-size";
  private final static int DEFAULT_PAGE_SIZE = 10;
  private final static String SHOW_FIELDS = "show-fields";
  private final static String THUMBNAIL = "thumbnail";


  public Map<String, String> getParameters(GuardianService.ApiType type)
  {

    Map<String, String> params = buildDefault();

    if (type != GuardianService.ApiType.SEARCH)
    {
      params.put("q", type.getQuery());
    }

    return params;
  }


  private Map<String, String> buildDefault()
  {
    Date today = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    Map<String, String> params = new HashMap<>();
    params.put(API_KEY, GuardianService.API_KEY);
    params.put(FORMAT, RESPONSE_FORMAT);
    params.put(FROM_DATE, dateFormat.format(today));
    params.put(NUM_PAGES, Integer.toString(DEFAULT_NUM_PAGES));
    params.put(PAGE_SIZE, Integer.toString(DEFAULT_PAGE_SIZE));
    params.put(SHOW_FIELDS, THUMBNAIL);
    return params;
  }


}
