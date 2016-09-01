package com.example.dcrelling.guardian.mocks;

import java.util.Map;

import com.example.dcrelling.guardian.services.GuardianArticleResponse;
import com.example.dcrelling.guardian.services.GuardianService;
import com.google.gson.Gson;
import retrofit2.http.QueryMap;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import rx.Observable;

/**
 * Created by dcrelling on 9/1/16.
 */

public class GuardianServiceMock implements GuardianService
{

  private final BehaviorDelegate<GuardianService> _delegate;
  private Gson _gson = new Gson();



  public GuardianServiceMock(BehaviorDelegate<GuardianService> delegate)
  {
    _delegate = delegate;
  }


  @Override
  public Observable<GuardianArticleResponse> getArticles(@QueryMap Map<String, String> queryParams)
  {
    GuardianArticleResponse response = _gson.fromJson("{\"response\":{\"status\":\"ok\",\"userTier\":\"developer\",\"total\":1884869,\"startIndex\":1,\"pageSize\":10,\"currentPage\":1,\"pages\":188487,\"orderBy\":\"newest\",\"results\":[{\"id\":\"politics/2016/sep/01/police-investigate-antisemitic-homophobic-online-rant-targeting-mp-ruth-smeeth\",\"type\":\"article\",\"sectionId\":\"politics\",\"sectionName\":\"Politics\",\"webPublicationDate\":\"2016-09-01T22:11:31Z\",\"webTitle\":\"Police investigate antisemitic and homophobic online abuse targeting MP\",\"webUrl\":\"https://www.theguardian.com/politics/2016/sep/01/police-investigate-antisemitic-homophobic-online-rant-targeting-mp-ruth-smeeth\",\"apiUrl\":\"https://content.guardianapis.com/politics/2016/sep/01/police-investigate-antisemitic-homophobic-online-rant-targeting-mp-ruth-smeeth\",\"isHosted\":false},{\"id\":\"us-news/live/2016/sep/01/trump-immigration-speech-clinton-biden-campaign-live\",\"type\":\"liveblog\",\"sectionId\":\"us-news\",\"sectionName\":\"US news\",\"webPublicationDate\":\"2016-09-01T22:10:53Z\",\"webTitle\":\"Hillary Clinton announces blowout $143m fundraising haul – campaign live\",\"webUrl\":\"https://www.theguardian.com/us-news/live/2016/sep/01/trump-immigration-speech-clinton-biden-campaign-live\",\"apiUrl\":\"https://content.guardianapis.com/us-news/live/2016/sep/01/trump-immigration-speech-clinton-biden-campaign-live\",\"isHosted\":false},{\"id\":\"us-news/2016/sep/01/st-georges-school-sexual-abuse-investigation\",\"type\":\"article\",\"sectionId\":\"us-news\",\"sectionName\":\"US news\",\"webPublicationDate\":\"2016-09-01T21:44:18Z\",\"webTitle\":\"One in five girls at St George's school in 1970s sexually abused by trainer: report\",\"webUrl\":\"https://www.theguardian.com/us-news/2016/sep/01/st-georges-school-sexual-abuse-investigation\",\"apiUrl\":\"https://content.guardianapis.com/us-news/2016/sep/01/st-georges-school-sexual-abuse-investigation\",\"isHosted\":false},{\"id\":\"film/2016/sep/01/jim-the-james-foley-story-review-us-journalist-syria-islamic-state\",\"type\":\"article\",\"sectionId\":\"film\",\"sectionName\":\"Film\",\"webPublicationDate\":\"2016-09-01T21:30:22Z\",\"webTitle\":\"Jim: The James Foley Story review – heartfelt film of brave frontline reporting\",\"webUrl\":\"https://www.theguardian.com/film/2016/sep/01/jim-the-james-foley-story-review-us-journalist-syria-islamic-state\",\"apiUrl\":\"https://content.guardianapis.com/film/2016/sep/01/jim-the-james-foley-story-review-us-journalist-syria-islamic-state\",\"isHosted\":false},{\"id\":\"tv-and-radio/2016/sep/01/the-night-of-riz-ahmed-hbo-murder-drama\",\"type\":\"article\",\"sectionId\":\"tv-and-radio\",\"sectionName\":\"Television & radio\",\"webPublicationDate\":\"2016-09-01T21:30:22Z\",\"webTitle\":\"The Night Of review: a frightened young man on the road to hell\",\"webUrl\":\"https://www.theguardian.com/tv-and-radio/2016/sep/01/the-night-of-riz-ahmed-hbo-murder-drama\",\"apiUrl\":\"https://content.guardianapis.com/tv-and-radio/2016/sep/01/the-night-of-riz-ahmed-hbo-murder-drama\",\"isHosted\":false},{\"id\":\"football/2016/sep/01/stoke-city-jack-butland-england-world-cup\",\"type\":\"article\",\"sectionId\":\"football\",\"sectionName\":\"Football\",\"webPublicationDate\":\"2016-09-01T21:30:22Z\",\"webTitle\":\"Stoke City’s Jack Butland faces a further two months on sidelines\",\"webUrl\":\"https://www.theguardian.com/football/2016/sep/01/stoke-city-jack-butland-england-world-cup\",\"apiUrl\":\"https://content.guardianapis.com/football/2016/sep/01/stoke-city-jack-butland-england-world-cup\",\"isHosted\":false},{\"id\":\"music/2016/sep/01/izzy-bizu-a-moment-of-madness-review-relentlessly-sweet-jazz-soul-pop\",\"type\":\"article\",\"sectionId\":\"music\",\"sectionName\":\"Music\",\"webPublicationDate\":\"2016-09-01T21:30:22Z\",\"webTitle\":\"Izzy Bizu: A Moment of Madness review – relentlessly sweet jazz-soul-pop\",\"webUrl\":\"https://www.theguardian.com/music/2016/sep/01/izzy-bizu-a-moment-of-madness-review-relentlessly-sweet-jazz-soul-pop\",\"apiUrl\":\"https://content.guardianapis.com/music/2016/sep/01/izzy-bizu-a-moment-of-madness-review-relentlessly-sweet-jazz-soul-pop\",\"isHosted\":false},{\"id\":\"culture/2016/sep/02/ngean-noonar-noongar-is-australias-first-indigenous-wikipedia-we-want-to-use-our-language\",\"type\":\"article\",\"sectionId\":\"culture\",\"sectionName\":\"Culture\",\"webPublicationDate\":\"2016-09-01T21:22:20Z\",\"webTitle\":\"Ngean noonar? Friday night is Noongar night and nobody speaks English | Monica Tan\",\"webUrl\":\"https://www.theguardian.com/culture/2016/sep/02/ngean-noonar-noongar-is-australias-first-indigenous-wikipedia-we-want-to-use-our-language\",\"apiUrl\":\"https://content.guardianapis.com/culture/2016/sep/02/ngean-noonar-noongar-is-australias-first-indigenous-wikipedia-we-want-to-use-our-language\",\"isHosted\":false},{\"id\":\"film/2016/sep/01/chicklit-review-mummy-porn-shenanigans-fail-to-arouse\",\"type\":\"article\",\"sectionId\":\"film\",\"sectionName\":\"Film\",\"webPublicationDate\":\"2016-09-01T21:15:21Z\",\"webTitle\":\"ChickLit review – mummy-porn shenanigans fail to arouse\",\"webUrl\":\"https://www.theguardian.com/film/2016/sep/01/chicklit-review-mummy-porn-shenanigans-fail-to-arouse\",\"apiUrl\":\"https://content.guardianapis.com/film/2016/sep/01/chicklit-review-mummy-porn-shenanigans-fail-to-arouse\",\"isHosted\":false},{\"id\":\"music/2016/sep/01/ward-thomas-cartwheels-review-slick-country-pop-and-millennial-whoops\",\"type\":\"article\",\"sectionId\":\"music\",\"sectionName\":\"Music\",\"webPublicationDate\":\"2016-09-01T21:14:21Z\",\"webTitle\":\"Ward Thomas: Cartwheels review – slick country pop and millennial whoops\",\"webUrl\":\"https://www.theguardian.com/music/2016/sep/01/ward-thomas-cartwheels-review-slick-country-pop-and-millennial-whoops\",\"apiUrl\":\"https://content.guardianapis.com/music/2016/sep/01/ward-thomas-cartwheels-review-slick-country-pop-and-millennial-whoops\",\"isHosted\":false}]}}", GuardianArticleResponse.class);
    return _delegate.returningResponse(response).getArticles(queryParams);
  }
}
