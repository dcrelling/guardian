package com.example.dcrelling.guardian.adapters;

import java.util.List;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.dcrelling.guardian.R;
import com.example.dcrelling.guardian.services.GuardianArticleResponse;

/**
 * Created by dcrelling on 8/21/16.
 */

public class ArticleAdapter extends ArrayAdapter<GuardianArticleResponse.Article>
{
  public ArticleAdapter(Context context, List<GuardianArticleResponse.Article> objects)
  {
    super(context, 0, objects);
  }


  @NonNull
  @Override
  public View getView(int position, View convertView, ViewGroup parent)
  {
    GuardianArticleResponse.Article article = getItem(position);
    if (convertView == null)
    {
      convertView = LayoutInflater.from(getContext()).inflate(R.layout.article_list_item, parent, false);
    }
    TextView webTitle = (TextView) convertView.findViewById(R.id.web_title);
    webTitle.setText(article.webTitle);
    return convertView;

  }
}