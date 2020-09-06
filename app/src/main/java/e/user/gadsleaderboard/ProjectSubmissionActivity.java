package e.user.gadsleaderboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectSubmissionActivity extends AppCompatActivity {
    private Button submitBtn;
    private EditText firstNameEt;
    private EditText lastNameEt;
    private EditText emailEt;
    private EditText githubLinkEt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_submission);
        Toolbar toolbar = findViewById(R.id.toolbarSubmit);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        submitBtn = findViewById(R.id.submitBtn);
        firstNameEt = findViewById(R.id.firstNameEditText);
        lastNameEt = findViewById(R.id.lastNameEditText);
        emailEt = findViewById(R.id.emailEditText);
        githubLinkEt = findViewById(R.id.githubLinkEditText);

        submitBtn.setOnClickListener(view -> {
            submitProject();
        });
    }

    private void submitProject() {
        if (validateUserInputs()){
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
            final AlertDialog alertDialog = alertBuilder.create();
            alertDialog.show();
            alertDialog.getWindow().setLayout(550, 600);
            LayoutInflater inflater = this.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.confirm_submission_dialog, null);
            alertDialog.getWindow().setContentView(dialogView);
            Button confirmSubmissionBtn = dialogView.findViewById(R.id.confirmProjectSubmissionBtn);
            confirmSubmissionBtn.setOnClickListener(view -> {
                alertDialog.dismiss();
                postRequestToGoogleServers();
            });
        }
    }

    private void postRequestToGoogleServers() {
        String firstName = firstNameEt.getText().toString().trim();
        String lastName = lastNameEt.getText().toString().trim();
        String email = emailEt.getText().toString().trim();
        String githubLink = githubLinkEt.getText().toString().trim();

        ApiClient.getGoogleDocsClient().submitProject(email, firstName, lastName, githubLink)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()){
                            Log.d("Tag", "---------Submit--------" + response.body());
                            AlertDialog.Builder alertBuilder = new
                                    AlertDialog.Builder(ProjectSubmissionActivity.this);
                            AlertDialog alertDialog = alertBuilder.create();
                            alertDialog.show();
                            alertDialog.getWindow().setLayout(550, 350);
                            LayoutInflater inflater = ProjectSubmissionActivity.this.getLayoutInflater();
                            View dialogView = inflater.inflate(R.layout.success_dialog, null);
                            alertDialog.getWindow().setContentView(dialogView);
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.d("Tag", "-------fail-------" + t.getMessage());
                        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(ProjectSubmissionActivity.this);
                        AlertDialog alertDialog = alertBuilder.create();
                        alertDialog.show();
                        alertDialog.getWindow().setLayout(550, 350);
                        LayoutInflater inflater = ProjectSubmissionActivity.this.getLayoutInflater();
                        View dialogView = inflater.inflate(R.layout.submission_fail_dialog, null);
                        alertDialog.getWindow().setContentView(dialogView);
                    }
                });
    }

    private boolean validateUserInputs() {
        if (firstNameEt.getText().toString().trim().equals("")){
            firstNameEt.setError("Name is required");
            return false;
        }
        if (lastNameEt.getText().toString().trim().equals("")){
            lastNameEt.setError("Last Name is required");
            return false;
        }
        if (emailEt.getText().toString().trim().equals("") ||
                !Patterns.EMAIL_ADDRESS.matcher(emailEt.getText().toString().trim()).matches()){
            emailEt.setError("Enter a valid Email");
            return false;
        }
        if (githubLinkEt.getText().toString().trim().equals("")){
            githubLinkEt.setError("GitHub Link is required");
            return false;
        }
        return true;
    }
}
