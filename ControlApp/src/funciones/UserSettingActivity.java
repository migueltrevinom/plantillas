package funciones;

import hand.ControlApp.R;
import android.app.ActionBar;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class UserSettingActivity extends PreferenceActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		addPreferencesFromResource(R.xml.settings);
		//el siguiente codigo es para poder generar el boton de back en el app icon
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        //termina
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.login_actions, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	    case android.R.id.home:
	    	onBackPressed();
	    	return true;
	    	
	        case R.id.action_settings:
	            
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	
	@Override
    public void onBackPressed() {
        super.onBackPressed();   
            finish();

    }
	
}
