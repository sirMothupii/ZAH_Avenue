package com.sirmothupii.zah_avenue;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Comment;

import java.util.List;

/**
 * Created by sirmothupii on 2017-12-19.
 */
class CommentsAdapter extends ArrayAdapter<Comment>
{
    public CommentsAdapter(Context context, List<Comment> comments)
    {
        super(context, android.R.layout.simple_list_item_1, comments);
    }

    //here we declare the variables
    public String profileUrl;
    public String username;

    // getView method (int position)
    // Default, takes the model (InstagramPhoto) toString()

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        //Variables are declared here

        // Take the data source at position (e.g. 0)
        // Get the data item
        Comment comment = getItem(position);

        // Check if we are using a recycled view
        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_comment, parent, false);
        }

        // Lookup the subview within the template
        ImageView imgProfile = convertView.findViewById(R.id.imgCommentProfile);
        TextView tvComment = convertView.findViewById(R.id.tvComment);
        TextView tvCommentTime = convertView.findViewById(R.id.tvCommentTime);

        // tvComment.setText(Html.fromHtml("<font color='#3f729b'><b>" + comment.username + "</b></font> " + comment.text));
        assert comment != null;
        // tvCommentTime.setText(comment.getRelativeTime());

        // Reset the images from the recycled view
        imgProfile.setImageResource(0);

        //Ask for the photo to be added to the image view based on the photo url
        //Background: Send a network request to the url, download the image bytes, convert into bitmap, insert bitmap into the image view
        //Picasso.with(getContext()).load(comment.profileUrl).into(imgProfile);

        // Return the view for that data item
        return convertView;
    }

    @Override
    public boolean isEnabled(int position)
    {
        // disables selection
        return false;
    }
}
