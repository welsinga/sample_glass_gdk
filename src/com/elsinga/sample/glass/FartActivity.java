package com.elsinga.sample.glass;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;

import com.google.android.glass.app.Card;

public class FartActivity extends Activity
{

  private static final int SOUND_PRIORITY = 1;
  private static final int MAX_STREAMS    = 1;

  private SoundPool        _soundPool;
  private int              _fartSoundId;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    _soundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
    _fartSoundId = _soundPool.load(this, R.raw.fart, SOUND_PRIORITY);
    _soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener()
    {
      @Override
      public void onLoadComplete(SoundPool soundPool, int mySoundId, int status)
      {
        playSound(_fartSoundId);
        showImage();
      }
    });
  }

  protected void showImage()
  {
    Card card = new Card(this);
    card.setText(R.string.main_card_text);
    card.setFootnote(R.string.main_card_footnote_text);
    card.setImageLayout(Card.ImageLayout.FULL);
    card.addImage(R.drawable.fart);
    View cardView = card.toView();
    setContentView(cardView);
  }

  /**
   * Plays the provided {@code soundId}.
   */
  private void playSound(int soundId)
  {
    _soundPool.play(soundId, 1, 1, SOUND_PRIORITY, 0, 1);
  }

}
