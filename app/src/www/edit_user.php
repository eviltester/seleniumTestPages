<?php
require_once 'default_globals.php';
redirectIfNotLoggedLoggedIn();

// load user details into page if they exist to populate the form
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>
      HTML Form Elements
    </title>

      <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
      <script src="//cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
      <script src="//cdnjs.cloudflare.com/ajax/libs/json2/20121008/json2.js"></script>
      <script src="./js/amplify/1.1.2/amplify.min.js" type="text/javascript"></script>

      <script type="text/javascript">

          var currentUser = {
              name: 'unknown',
              password: 'password',
              description: '',
              interests: '',
              gender: 'unknown',
              education: '',
              startrek: ''
          };


          var userNameCookieValue = $.cookie('<?php echo($loggedInCookieName) ?>');
          var localStoreUser = amplify.store("user_" + userNameCookieValue);

          if (typeof localStoreUser === "undefined") {
              // we have not defined the user locally yet
              // so construct from cookie

              if (typeof userNameCookieValue !== "undefined") {
                  currentUser.name = userNameCookieValue;
              }

          }else{
              // load in the user details from local storage
              currentUser.name = localStoreUser.username;
              currentUser.description = localStoreUser.description;
              currentUser.interests = localStoreUser.interests;
              currentUser.gender = localStoreUser.gender;
              currentUser.education = localStoreUser.education;
              currentUser.startrek = localStoreUser.startrek;
          }



      </script>
  </head>
  <body>

  <?php
  displayHeader();
  ?>
    <form name="EditUserDetails" action=
    "edit_user_form_processor.php"
    method="post" id="EditUserDetailsForm">
      <table border="1" cellpadding="5">
        <!-- ...in a big HTML table. -->
          <p id="formprompt">Wait...details are loading</p>
        <tr>
          <td>
            Username:<br />
            <input type="text" name="username" size="15" />
          </td>
        </tr>
        <tr>
          <td>
            Description:<br />
            <textarea cols="40" name="userdescription" rows="6"></textarea>
          </td>
        </tr>
        <tr>
          <td>
            Interests:<br />
            <input type="checkbox" name="checkboxes[]" value=
            "computers" />Computers
						<input type="checkbox" name="checkboxes[]"
            value="games" />Games
						<input type="checkbox" name=
            "checkboxes[]" value="books"/>Books
          </td>
        </tr>
        <tr>
          <td>
            Gender:<br />
            <input type="radio" name="radioval" value="male" />Male
            <input type="radio" name="radioval" value="female" />Female
			<input type="radio" name="radioval" value="unknown" />Unknown
          </td>
        </tr>
        <tr>
          <td>
            Education<br />
            <select multiple="multiple" name="multipleselect[]"
            size="4">
              <option value="school">
                School
              </option>
              <option value="college">
                College
              </option>
              <option value="university">
                University
              </option>
              <option value="life">
                Life
              </option>
            </select>
          </td>
        </tr>
        <tr>
          <td>
            Favourite Star Trek Series:<br />
            <select name="dropdown">
              <option value="original">
                Original Series
              </option>
              <option value="nextgen">
                Next Generation
              </option>
              <option value="voyager">
                Voyager
              </option>
              <option value="enterprise">
                Enterprise
              </option>
              <option value="deepspace">
                Deep Space Nine
              </option>
              <option value="animated">
                Animated Series
              </option>
            </select>
          </td>
        </tr>
        <tr>
          <td>
            <input type="reset" name="submitbutton" value=
            "cancel" /> 
			<input type="submit" name="submitbutton"
            value="submit" />
          </td>
        </tr>
      </table>
    </form>

  <script type="text/javascript">
      // set the default values in the form
      //debugger;
      $('input[name="username"]').val(currentUser.name);
      $('textarea[name="userdescription"]').text(currentUser.description);
      $('input[name="checkboxes[]"]').each(function(){
              $(this).prop("checked", currentUser.interests.indexOf($(this).attr("value"))!=-1)
          }
      );
      $('input[name="radioval"]').each(function(){
            $(this).prop("checked", $(this).attr("value") == currentUser.gender)
          }
        );
      $('select[name="multipleselect[]"] > option').each(function(){
              $(this).prop("selected", currentUser.education.indexOf($(this).attr("value"))!=-1)
          }
      );
      $('select[name="dropdown"] > option').each(function(){
              $(this).prop("selected", currentUser.startrek.indexOf($(this).attr("value"))!=-1)
          }
      );
      $('#formprompt').text("Please Amend Details");

  </script>
  </body>
</html>
