package cn.jiguang.imui.messages.viewholder;

import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import cn.jiguang.imui.R;
import cn.jiguang.imui.commons.models.IMessage;
import cn.jiguang.imui.messages.MessageListStyle;


public class VideoViewHolder<MESSAGE extends IMessage> extends AvatarViewHolder<MESSAGE> {

    private final ImageView mImageCover;
    private final ImageView mImagePlay;
    private final TextView mTvDuration;

    public VideoViewHolder(RecyclerView.Adapter adapter, View itemView, boolean isSender) {
        super(adapter, itemView, isSender);
        mImageCover = (ImageView) itemView.findViewById(R.id.aurora_iv_msgitem_cover);
        mImagePlay = (ImageView) itemView.findViewById(R.id.aurora_iv_msgitem_play);
        mTvDuration = (TextView) itemView.findViewById(R.id.aurora_tv_duration);
    }

    @Override
    public void onBind(final MESSAGE message) {
        super.onBind(message);

        Bitmap thumb = ThumbnailUtils.createVideoThumbnail(message.getMediaFilePath(),
                MediaStore.Images.Thumbnails.MINI_KIND);
        mImageCover.setImageBitmap(thumb);
        mImageCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMsgClickListener.onMessageClick(message);
            }
        });
        mImageCover.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {
                mMsgLongClickListener.onMessageLongClick(message);
                return false;
            }
        });

        String durationStr = String.format(Locale.CHINA, "%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(message.getDuration()),
                TimeUnit.MILLISECONDS.toSeconds(message.getDuration()));
        mTvDuration.setText(durationStr);
    }

    @Override
    public void applyStyle(MessageListStyle style) {
    }
}