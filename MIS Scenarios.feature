Feature: MIS Scenarios

  Background:
    Given user is on homepage

  Scenario: Login with correct credentials
    When for the Account_login page, user enters "ayush.garg@geminisolutions.com " as input for Username
    When for the Account_login page, user enters "Gemini@123 " as input for Password
    When for the Account_login page, user clicks on Sign_in button
    Then for the Dashboard_index page, user verifies Location div is visible

  Scenario: Login with incorrect credentials
    When for the Account_login page, user enters "ayush.garg@geminisolutions.com " as input for Username
    When for the Account_login page, user enters "Gemini@1234 " as input for Password
    When for the Account_login page, user clicks on Sign_in button
    Then for the Account_login page, user verifies The_username_or_password_entered_4_attempts_left div is visible

  Scenario: Only enter username while doing login
    When for the Account_login page, user enters "ayush.garg@geminisolutions.com " as input for Username
    When for the Account_login page, user clicks on Sign_in button
    Then for the Account_login page, user verifies Password_required div is visible

  Scenario: Only enter password while doing login
    When for the Account_login page, user enters "Gemini@1234 " as input for Password
    When for the Account_login page, user clicks on Sign_in button
    Then for the Account_login page, user verifies Username_required div is visible

  Scenario: Do not enter creds while doing login
    When for the Account_login page, user clicks on Sign_in button
    Then for the Account_login page, user verifies Enter_your_username_and_password div is visible

  Scenario: Empy Submit LNSA
    When I login with "ayush.garg@geminisolutions.com" username and "Gemini@123" password
    When for the Account_login page, user clicks on LNSA button
    When for the Dashboard_index page, user clicks on Apply_LNSA button
    When for the Lnsa_apply page, user clicks on Submit button
    Then for the Lnsa_apply page, user verifies Warning div is visible

  Scenario: Configure Timesheet
    When I login with "ayush.garg@geminisolutions.com" username and "Gemini@123" password
    When for the Account_login page, user clicks on TimeSheet button
    When for the Dashboard_index page, user clicks on Configure_Timesheet button
    When for the Timesheet_configuretimesheet page, user clicks on Save button
    Then for the Timesheet_configuretimesheet page, user verifies Success div is visible

  Scenario: MIS_dashboard_editprofileCard_updateValid_phoneNumber
    When I login with "ayush.garg@geminisolutions.com" username and "Gemini@123" password
    When for the Account_login page, user clicks on btnUpdateProfile button
    When for the Dashboard_index page, user enters "7290928675 " as input for Mobile_number
    When for the Dashboard_index page, user enters "123 Contact " as input for Emergency
    When for the Dashboard_index page, user clicks on Update button
    Then for the Dashboard_index page, user verifies Warning div is visible

  Scenario: view document
    When for the Account_login page, user enters "ayush.garg@geminisolutions.com " as input for Username
    When for the Account_login page, user enters "Gemini@123 " as input for Password
    When for the Account_login page, user clicks on Sign_in button
    When for the Dashboard_index page, user clicks on Knowledge_Base button
    When for the Dashboard_index page, user clicks on View_Shared_Documents button
    Then for the Dashboard_index page, user verifies Modern_Perl div is visible

  Scenario: submit feedback
    When for the Account_login page, user enters "ayush.garg@geminisolutions.com " as input for Username
    When for the Account_login page, user enters "Gemini@123 " as input for Password
    When for the Account_login page, user clicks on Sign_in button
    When for the Dashboard_index page, user clicks on Feedback button
    When for the Dashboard_index page, user clicks on Submit_Feedback button
    When for the Feedback_submitfeedback page, user clicks on Provide_Feedback button
    When for the Feedback_submitfeedback page, user enters "test " as input for feedback
    When for the Feedback_submitfeedback page, user clicks on Submit button
    Then for the Feedback_submitfeedback page, user verifies test div is visible

  Scenario: forgot password while login
    When for the Account_login page, user clicks on forgot_password button
    When for the Account_login page, user enters "ayush.garg@geminisolutions.com " as input for Username
    When for the Account_login page, user clicks on Reset_Password button

  Scenario: delegate review request
    When for the Account_login page, user enters "ayush.garg@geminisolutions.com " as input for Username
    When for the Account_login page, user enters "Gemini@123 " as input for Password
    When for the Account_login page, user clicks on Sign_in button
    When for the Dashboard_index page, user clicks on Leave_Management button
    When for the Dashboard_index page, user clicks on Review_Request button
    When for the Leavemanagement_reviewrequest page, user clicks on Delegate button
    Then for the Leavemanagement_reviewrequest page, user verifies Service_Unavailable div is visible

  Scenario: new request for reimbursement
    When for the Account_login page, user enters "ayush.garg@geminisolutions.com " as input for Username
    When for the Account_login page, user enters "Gemini@123 " as input for Password
    When for the Account_login page, user clicks on Sign_in button
    When for the Dashboard_index page, user clicks on Reimbursement button
    When for the Dashboard_index page, user clicks on MyReimbursement button
    Then for the Reimbursement_myreimbursement page, user verifies Reimbursement_Request div is visible
    When for the Reimbursement_myreimbursement page, user clicks on New_Request button
    Then for the Reimbursement_myreimbursement page, user verifies Reimbursement_Form div is visible

  Scenario: update holidays
    When for the Account_login page, user enters "ayush.garg@geminisolutions.com " as input for Username
    When for the Account_login page, user enters "Gemini@123 " as input for Password
    When for the Account_login page, user clicks on Sign_in button
    When for the Dashboard_index page, user clicks on Customize_your_holidays button
    When for the Dashboard_index page, user clicks on Update button
    Then for the Dashboard_index page, user verifies Widget_Reloading div is visible

  Scenario: Delete  skill
    When for the Account_login page, user enters "ayush.garg@geminisolutions.com " as input for Username
    Then for the Account_login page, user verifies Manage_Skills div is visible
    When for the Account_login page, user enters "Gemini@123 " as input for Password
    When for the Account_login page, user clicks on Sign_in button
    When for the Dashboard_index page, user clicks on MongoDB button
    When for the Dashboard_index page, user clicks on Delete button
    When for the Dashboard_index page, user clicks on Yes button
    Then for the Dashboard_index page, user verifies Success div is visible

  Scenario: bulk approve review request
    When for the Account_login page, user enters "ayush.garg@geminisolutions.com " as input for Username
    When for the Account_login page, user enters "Gemini@123 " as input for Password
    When for the Account_login page, user clicks on Sign_in button
    When for the Dashboard_index page, user clicks on Leave_Management button
    When for the Dashboard_index page, user clicks on Review_Request button
    When for the Leavemanagement_reviewrequest page, user clicks on Approve button
    Then for the Leavemanagement_reviewrequest page, user verifies warning div is visible

  Scenario: search in system upgrade request
    When for the Account_login page, user enters "ayush.garg@geminisolutions.com " as input for Username
    When for the Account_login page, user enters "Gemini@123 " as input for Password
    When for the Account_login page, user clicks on Sign_in button
    When for the Dashboard_index page, user clicks on Apply button
    When for the Dashboard_index page, user clicks on System_Upgrade_Request button
    When for the Dashboard_index page, user enters "Alphathum " as input for Search
    Then for the Dashboard_index page, user verifies Alphathum div is visible

  Scenario: search for document in shared documents
    When for the Account_login page, user enters "ayush.garg@geminisolutions.com " as input for Username
    When for the Account_login page, user enters "Gemini@123 " as input for Password
    When for the Account_login page, user clicks on Sign_in button
    When for the Dashboard_index page, user clicks on knowledge_base button
    When for the Dashboard_index page, user clicks on view_shared_documents button
    When for the Dashboard_index page, user enters "Autosys " as input for search
    Then for the Dashboard_index page, user verifies Autosys div is visible

  Scenario: search emplyee in employee directory
    When for the Account_login page, user enters "ayush.garg@geminisolutions.com " as input for Username
    When for the Account_login page, user enters "Gemini@123 " as input for Password
    When for the Account_login page, user clicks on Sign_in button
    When for the Dashboard_index page, user clicks on employee_directory button
    When for the Directory_employeedirectory page, user enters "Raghav " as input for search
    Then for the Directory_employeedirectory page, user verifies Raghav div is visible

  Scenario: search policy
    When for the Account_login page, user enters "ayush.garg@geminisolutions.com " as input for Username
    When for the Account_login page, user enters "Gemini@123 " as input for Password
    When for the Account_login page, user clicks on Sign_in button
    When for the Dashboard_index page, user clicks on policy button
    When for the Dashboard_index page, user clicks on view_policies button
    When for the Policy_viewpolicy page, user enters "Access " as input for Search
    Then for the Policy_viewpolicy page, user verifies Access_Policy div is visible

  Scenario: search lunch`
    When for the Account_login page, user enters "ayush.garg@geminisolutions.com " as input for Username
    When for the Account_login page, user enters "Gemini@123 " as input for Password
    When for the Account_login page, user clicks on Sign_in button
    When for the Dashboard_index page, user clicks on Apply button
    When for the Dashboard_index page, user clicks on Lunch button
    When for the Dashboard_index page, user clicks on Next button
    Then for the Dashboard_index page, user verifies Canaan_Tower div is visible

  Scenario: save timesheet
    When for the Account_login page, user enters "ayush.garg@geminisolutions.com " as input for Username
    When for the Account_login page, user enters "Gemini@123 " as input for Password
    When for the Account_login page, user clicks on Sign_in button
    When for the Dashboard_index page, user clicks on TimeSheet button
    When for the Dashboard_index page, user clicks on Configure_Timesheet button
    When for the Timesheet_configuretimesheet page, user clicks on Save button
    Then for the Timesheet_configuretimesheet page, user verifies Success div is visible

  Scenario: serach in organization structure
    When for the Account_login page, user enters "ayush.garg@geminisolutions.com " as input for Username
    When for the Account_login page, user enters "Gemini@123 " as input for Password
    When for the Account_login page, user clicks on Sign_in button
    When for the Dashboard_index page, user clicks on Organization_Structure button
    When for the Organization_organizationstructure page, user enters "Raghav " as input for search
    Then for the Organization_organizationstructure page, user verifies Raghav_Suneja div is visible

  Scenario: Checking for Pagination in Policies
    When I login with "ayush.garg@geminisolutions.com" username and "Gemini@123" password
    When for the Account_login page, user clicks on Policy element
    When for the Dashboard_index page, user clicks on View_Policies button
    When for the Policy_viewpolicy page, user clicks on Next button

  Scenario: Copy Timesheet
    When I login with "ayush.garg@geminisolutions.com" username and "Gemini@123" password
    When for the Account_login page, user clicks on TimeSheet button
    When for the Dashboard_index page, user clicks on Create_Timesheet button
    When for the Timesheet_createtimesheet page, user clicks on Ok button
    When for the Timesheet_createtimesheet page, user clicks on Copy_From button
    Then for the Timesheet_createtimesheet page, user verifies Copy_timesheet div is visible
    When for the Timesheet_createtimesheet page, user selects "2024 " from Year dropdown
    When for the Timesheet_createtimesheet page, user clicks on Close button

  Scenario: Edit Task Template  with same data
    When I login with "ayush.garg@geminisolutions.com" username and "Gemini@123" password
    When for the Account_login page, user clicks on TimeSheet button
    When for the Dashboard_index page, user clicks on Manage_Task_Template button
    When for the Timesheet_managetasktemplates page, user clicks on Edit button
    When for the Timesheet_managetasktemplates page, user clicks on Update button
    Then for the Timesheet_managetasktemplates page, user verifies Warning div is visible

  Scenario: Wrong Search from Task Template  
    When I login with "ayush.garg@geminisolutions.com" username and "Gemini@123" password
    When for the Account_login page, user clicks on TimeSheet button
    When for the Dashboard_index page, user clicks on Manage_Task_Template button
    When for the Timesheet_managetasktemplates page, user enters "random " as input for Search
    Then for the Timesheet_managetasktemplates page, user verifies No_matching_records_found div is visible

  Scenario: Verify Date of partcular financial year is present
    When I login with "ayush.garg@geminisolutions.com" username and "Gemini@123" password
    When for the Account_login page, user selects "January 2024 " from My_Attendance dropdown

  Scenario: change country in profile section
    When I login with "ayush.garg@geminisolutions.com" username and "Gemini@123" password
    When for the Account_login page, user clicks on edit button
    When for the Dashboard_index page, user clicks on Update_Address button
    When for the Dashboard_index page, user selects "Hungary " from Country dropdown
    When for the Dashboard_index page, user selects "Bekes " from State dropdown
    When for the Dashboard_index page, user selects "Bekes " from City dropdown

  Scenario: Customize your holidays
    When I login with "ayush.garg@geminisolutions.com" username and "Gemini@123" password
    When for the Account_login page, user clicks on Customize_your_holidays button
    When for the Dashboard_index page, user clicks on Update button
    When for the Dashboard_index page, user clicks on Okay button
    Then for the Dashboard_index page, user verifies Location div is visible

  Scenario: Check DC Hierarchy
    When I login with "ayush.garg@geminisolutions.com" username and "Gemini@123" password
    When for the Account_login page, user clicks on EC_DC_Hierarchy button
    When for the Dashboard_index page, user clicks on Delivery_Council button
    Then for the Dashboard_index page, user verifies Accounts div is visible

  Scenario: Check Chair for Java EC
    When I login with "ayush.garg@geminisolutions.com" username and "Gemini@123" password
    When for the Account_login page, user clicks on EC_DC_Hierarchy button
    When for the Dashboard_index page, user clicks on Engineering_Council button
    Then for the Dashboard_index page, user verifies Verify_Ritika_Jain_chair_Java div is <subaction>

  Scenario: Click on Apply lunch
    When for the Account_login page, user enters "ayush.garg@geminisolutions.com " as input for Username
    When for the Account_login page, user enters "Gemini@123 " as input for Password
    When for the Account_login page, user clicks on Sign_in button
    When for the Dashboard_index page, user clicks on btnUpdateProfile button
    Then for the Dashboard_index page, user verifies Location div is visible
    When for the Dashboard_index page, user enters "234 " as input for contactNo
    When for the Dashboard_index page, user clicks on Update button

  Scenario: MIS_Timesheet_Open_TimeSheetTab
    When I login with "ayush.garg@geminisolutions.com" username and "Gemini@123" password
    When for the Account_login page, user clicks on TimeSheet button

  Scenario: MIS_Timesheet_Open_ConfigureTimeSheetTab
    When I login with "ayush.garg@geminisolutions.com" username and "Gemini@123" password
    When for the Account_login page, user clicks on TimeSheet button
    When for the Dashboard_index page, user clicks on Configure_Timesheet button
    Then for the Timesheet_configuretimesheet page, user verifies Assigned_Projects div is visible

  Scenario: MIS_Timesheet_ConfigTimeSheet_data consistensy_Validation
    When I login with "ayush.garg@geminisolutions.com" username and "Gemini@123" password
    When for the Account_login page, user clicks on TimeSheet button
    When for the Dashboard_index page, user clicks on Configure_Timesheet button
    Then for the Timesheet_configuretimesheet page, user verifies No_data_available_in_Table div is visible

  Scenario: MIS_Timesheet_ConfigTimeSheet_SearchFIlter_Validation

    When I login with "ayush.garg@geminisolutions.com" username and "Gemini@123" password
    When for the Account_login page, user clicks on TimeSheet button
    When for the Dashboard_index page, user clicks on Configure_Timesheet button
    When for the Timesheet_configuretimesheet page, user enters "mis " as input for Search
    Then for the Timesheet_configuretimesheet page, user verifies No_data_available_in_Table div is visible

  Scenario: MIS_Timesheet_ConfigTimeSheet_popup_Validation
    When I login with "ayush.garg@geminisolutions.com" username and "Gemini@123" password
    When for the Account_login page, user clicks on TimeSheet button
    When for the Dashboard_index page, user clicks on Configure_Timesheet button
    When for the Timesheet_configuretimesheet page, user clicks on Save button
    Then for the Timesheet_configuretimesheet page, user verifies Success div is visible

  Scenario: MIS_Timesheet_Open_CreateTimeSheetTab
    When I login with "ayush.garg@geminisolutions.com" username and "Gemini@123" password
    When for the Account_login page, user clicks on TimeSheet button
    When for the Dashboard_index page, user clicks on Create_Timesheet button
    Then for the Timesheet_createtimesheet page, user verifies Please_note div is visible

  Scenario: MIS_Timesheet_CreateTimesheet_PleaseNotePopup_Validation
    When I login with "ayush.garg@geminisolutions.com" username and "Gemini@123" password
    When for the Account_login page, user clicks on TimeSheet button
    When for the Dashboard_index page, user clicks on Create_Timesheet button
    When for the Timesheet_createtimesheet page, user clicks on Ok button
    Then for the Timesheet_createtimesheet page, user verifies Create_TimeSheet div is visible

  Scenario: TimeSheet_ClickonNextButtonWeek
    When I login with "ayush.garg@geminisolutions.com" username and "Gemini@123" password
    When for the Account_login page, user clicks on TimeSheet button
    When for the Dashboard_index page, user clicks on Create_Timesheet button
    When for the Timesheet_createtimesheet page, user clicks on OK button
    When for the Timesheet_createtimesheet page, user clicks on btnNextWeek button
    Then for the Timesheet_createtimesheet page, user verifies Warning div is visible

  Scenario: TimeSheet_ClickonPreviousButtonWeek
    When I login with "ayush.garg@geminisolutions.com" username and "Gemini@123" password
    When for the Account_login page, user clicks on TimeSheet button
    When for the Dashboard_index page, user clicks on Create_Timesheet button
    When for the Timesheet_createtimesheet page, user clicks on OK button
    When for the Timesheet_createtimesheet page, user clicks on btnPreviousWeek button
    Then for the Timesheet_createtimesheet page, user verifies Week_14 div is visible

  Scenario: MIS_LeaveManagment_ViewRequestStatus_DateRange
    When I login with "ayush.garg@geminisolutions.com" username and "Gemini@123" password
    When for the Account_login page, user clicks on Leave_Management button
    When for the Dashboard_index page, user clicks on View_Request_Status button
    Then for the Leavemanagement_leaverequeststatus page, user verifies select2-financialYearScroll-container div is visible

  Scenario: MIS_LeaveManagment_ViewRequestStatus_Export
    When I login with "ayush.garg@geminisolutions.com" username and "Gemini@123" password
    When for the Account_login page, user clicks on Leave_Management button
    When for the Dashboard_index page, user clicks on View_Request_Status button
    When for the Leavemanagement_leaverequeststatus page, user clicks on Export button

  Scenario: MIS_LeaveManagment_ViewRequestStatus_Search
    When I login with "ayush.garg@geminisolutions.com" username and "Gemini@123" password
    When for the Account_login page, user clicks on Leave_Management button
    When for the Dashboard_index page, user clicks on View_Request_Status button
    When for the Leavemanagement_leaverequeststatus page, user enters "mis " as input for Search
    Then for the Leavemanagement_leaverequeststatus page, user verifies No_data_available_in_table div is visible

  Scenario: MIS_LNSA_ApplyLNSA_PreviousDates
    When I login with "ayush.garg@geminisolutions.com" username and "Gemini@123" password
    When for the Account_login page, user clicks on LNSA button
    When for the Dashboard_index page, user clicks on Apply_LNSA button
    When for the Lnsa_apply page, user clicks on btnPreviousMonth button

  Scenario: apply lnsa
    When for the Account_login page, user enters "ayush.garg@geminisolutions.com " as input for Username
    When for the Account_login page, user enters "Gemini@123 " as input for Password
    When for the Account_login page, user clicks on Sign_in button
    When for the Dashboard_index page, user clicks on LNSA button
    When for the Dashboard_index page, user clicks on Apply_LNSA button
    When for the Lnsa_apply page, user clicks on Monday button
    When for the Lnsa_apply page, user clicks on Submit button