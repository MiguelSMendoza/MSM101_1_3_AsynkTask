package es.netrunners.asynctask;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class AsynkTaskActivity extends Activity {
	ProgressBar progress;
	Button btnStart;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		progress = (ProgressBar) findViewById(R.id.progressBar);
		btnStart = (Button) findViewById(R.id.btnStart);
		btnStart.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				progress.setProgress(0);
				new AsyncProgress().execute();
			}
		});
	}

	public class AsyncProgress extends AsyncTask<Void, Integer, String> {

		@Override
		protected void onPreExecute() {
			Toast.makeText(getApplicationContext(),
					"Asynchronous Progress Started !!", Toast.LENGTH_LONG)
					.show();
			btnStart.setEnabled(false);
		}

		@Override
		protected String doInBackground(Void... params) {

			try {
				Thread.sleep(1000);
				publishProgress(25);
				Thread.sleep(2000);
				publishProgress(50);
				Thread.sleep(3000);
				publishProgress(90);
				Thread.sleep(2000);

			} catch (InterruptedException e) {

			}
			String str = "Asynchronous Progress Finished !!";
			return str;
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			progress.setProgress(values[0]);
		}

		@Override
		protected void onPostExecute(String result) {
			progress.setProgress(100);
			Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG)
					.show();
			btnStart.setEnabled(true);
		}

	}
}