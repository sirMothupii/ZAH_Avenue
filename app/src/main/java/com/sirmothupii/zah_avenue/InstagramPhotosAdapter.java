package com.sirmothupii.zah_avenue;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sirmothupii on 2017-12-23.
 */
class InstagramPhotosAdapter extends ArrayAdapter<com.sirmothupii.zah_avenue.InstagramPhoto>
{
    public InstagramPhotosAdapter(Context context, List<com.sirmothupii.zah_avenue.InstagramPhoto> photos)
    {
        super(context, android.R.layout.simple_list_item_1, photos);
    }

    // getView method (int position)
    // Default, takes the model (InstagramPhoto) toString()

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        // Take the data source at position (e.g. 0)
        // Get the data item
        com.sirmothupii.zah_avenue.InstagramPhoto photo = getItem(position);

        // Check if we are using a recycled view
        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_photo, parent, false);
        }

        // Lookup the subview within the template
        ImageView imgProfile = convertView.findViewById(R.id.imgProfile);
        ImageView imgPhoto = convertView.findViewById(R.id.imgPhoto);
        TextView tvUsername = convertView.findViewById(R.id.tvUsername);
        TextView tvTime = convertView.findViewById(R.id.tvTime);
        TextView tvLikes = convertView.findViewById(R.id.tvLikes);
        TextView tvCaption = convertView.findViewById(R.id.tvCaption);
        TextView tvViewAllComments = convertView.findViewById(R.id.tvViewAllComments);
        TextView tvComment1 = convertView.findViewById(R.id.tvComment1);
        TextView tvComment2 = convertView.findViewById(R.id.tvComment2);

        // Populate the subviews (textfield, imageview) with the correct data
        tvUsername.setText(photo.username);
        tvTime.setText(photo.getRelativeTime());
        tvLikes.setText(String.format("%d likes", photo.likesCount));
        if (photo.caption != null)
        {
            tvCaption.setText(Html.fromHtml("<font color='#3f729b'><b>" + photo.username + "</b></font> " + photo.caption));
            tvCaption.setVisibility(View.VISIBLE);
        }
        else
            {
            tvCaption.setVisibility(View.GONE);
        }

        if (photo.commentsCount > 0)
        {
            tvViewAllComments.setText(String.format("view all %d comments", photo.commentsCount));
            // set click handler for view all comments
            tvViewAllComments.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    //onClick intents to view photo and comments
                    Intent intent = new Intent(getContext(), Comments.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    InstagramPhoto ip = getItem(position);
                    intent.putExtra("id", ip.id);
                    getContext().startActivity(intent);
                }
            });
            tvViewAllComments.setVisibility(View.VISIBLE);
        }
        else
            {
            tvViewAllComments.setVisibility(View.GONE);
        }

        // Set last 2 comments
        if (photo.comment1 != null)
        {
            tvComment1.setText(Html.fromHtml("<font color='#3f729b'><b>" + photo.user1 + "</b></font> " + photo.comment1));
            tvComment1.setVisibility(View.VISIBLE);
        }
        else
            {
            tvComment1.setVisibility(View.GONE);
        }

        if (photo.comment2 != null)
        {
            tvComment2.setText(Html.fromHtml("<font color='#3f729b'><b>" + photo.user2 + "</b></font> " + photo.comment2));
            tvComment2.setVisibility(View.VISIBLE);
        }
        else
            {
            tvComment2.setVisibility(View.GONE);
        }

        // use device width for photo height
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        imgPhoto.getLayoutParams().height = displayMetrics.widthPixels;

        // Reset the images from the recycled view
        imgProfile.setImageResource(0);
        imgPhoto.setImageResource(0);

        // Ask for the photo to be added to the imageview based on the photo url
        // Background: Send a network request to the url, download the image bytes, convert into bitmap, insert bitmap into the imageview
        /**Picasso.with(getContext()).load(photo.profileUrl).into(imgProfile);
        Picasso.with(getContext()).load(photo.imageUrl).placeholder(R.drawable.instagram_glyph_on_white).into(imgPhoto);*/
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
