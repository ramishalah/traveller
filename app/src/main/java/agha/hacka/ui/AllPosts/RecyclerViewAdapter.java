package agha.hacka.ui.AllPosts;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import agha.hacka.R;
import agha.hacka.ui.AllPosts.AllPostsPOJO.PostPojo;
import agha.hacka.ui.DisplayPost.DisplayPost;
import agha.hacka.ui.Map.MapsActivity;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<PostPojo> list ;

    public RecyclerViewAdapter(Context c, ArrayList<PostPojo> list) {
        this.context = c;
        this.list = list ;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_rv_cell,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        // set tag to get position
        holder.title.setTag(position);

        holder.date.setText(list.get(position).getCreatedAt().toString());
        holder.title.setText(list.get(position).getMetadata().getTitle().toString());
        if (list.get(position).getPostImage() != null)
            Ion.with(context)
                    .load(list.get(position).getPostImage())
                    .withBitmap()
                    .intoImageView(holder.image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image ;
        TextView date ;
        TextView title;
        Intent i ;
        ImageView location ;

        /* constructor */
        public ViewHolder(View itemView) {
            super(itemView);
            // init
            image = (ImageView) itemView.findViewById(R.id.image);
            date = (TextView) itemView.findViewById(R.id.date);
            title = (TextView) itemView.findViewById(R.id.content);
            location = (ImageView) itemView.findViewById(R.id.location);

            i = new Intent(context, DisplayPost.class);

            /* click listener on elements */
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("Clicked","Clicked");
                    i.putExtra("title",title.getText().toString().trim());
                    i.putExtra("date",date.getText().toString().trim());
                    i.putExtra("description",list.get((int)title.getTag()).getDescription().toString());
                    i.putExtra("url",list.get((int)title.getTag()).getUser().getUrl().toString());
                    i.putExtra("name",list.get((int)title.getTag()).getUser().getFullName().toString());
                    i.putExtra("email",list.get((int)title.getTag()).getUser().getEmail().toString());
                    put();
                    context.startActivity(i);
                }
            });
            // location
            location.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    double LOG = list.get((int)title.getTag()).getLongitude();
                    double LAT = list.get((int)title.getTag()).getLatitude();
                    Intent i = new Intent(context, MapsActivity.class);
                    i.putExtra("LNG",LOG);
                    i.putExtra("LAT",LAT);
                    context.startActivity(i);
                }
            });

        }

        private Bitmap convertImageViewToBitmap(ImageView v){
            Bitmap bm = Ion.with(v).getBitmap();
            return bm;
        }

        private void put(){
            // put bitmap
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            convertImageViewToBitmap(image).compress(Bitmap.CompressFormat.JPEG, 50, stream);
            byte[] byteArray = stream.toByteArray();
            i.putExtra("image",byteArray);
        }
    }
}
