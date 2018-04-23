function pomodoro25Min() {
    return new Date(new Date().valueOf() + 25 * 60 * 1000);
}

function shortBreak5Min() {
    return new Date(new Date().valueOf() + 5 * 60 * 1000);
}

function longBreak10Min() {
    return new Date(new Date().valueOf() + 10 * 60 * 1000);
}


var shortBreakCountDown = false;
var longBreakCountDown = false;
var pomodoroCountDown = false;

//On click of short break button save short break in db and start short break timer
$("#shortBreak").click(function () {
    $('.timeElapsed').timer('remove');
    console.log("inside onclick");
    if (longBreakCountDown) {
        $("#longBreak").countdown('pause');
    }
    if (pomodoroCountDown) {
        $("#pomodoro").countdown('pause');
    }
    saveTask("Short break");
    shortBreakCountDown = true;
    $('#shortBreak').countdown(shortBreak5Min(), function (event) {
        $(".countdown").html(event.strftime('%M:%S'));
    }).on('finish.countdown', function () {
    });

});

//On click of long break button save the long break in db and start long break timer
$("#longBreak").click(function () {
    console.log("inside onclick");
    $('.timeElapsed').timer('remove');
    if (shortBreakCountDown) {
        $("#shortBreak").countdown('pause');
    }
    if (pomodoroCountDown) {
        $("#pomodoro").countdown('pause');
    }
    saveTask("Long break");
    longBreakCountDown = true;
    $('#longBreak').countdown(longBreak10Min(), function (event) {
        $(".countdown").html(event.strftime('%M:%S'));
    });
});

//On click of pomodoro button save the pomodoro in db and start pomodoro timer
$("#pomodoro").click(function () {
    var taskName = $("#taskName").val();
    console.log("inside onclick");
    $('.timeElapsed').timer('remove');
    if (shortBreakCountDown) {
        $("#shortBreak").countdown('pause');
    }
    if (longBreakCountDown) {
        $("#longBreak").countdown('pause');
    }
    saveTask(taskName);
    pomodoroCountDown = true;
    $('#pomodoro').countdown(pomodoro25Min(), function (event) {
        $(".countdown").html(event.strftime('%M:%S'));
    })
});

//Ajax call to save the task
function saveTask(taskName) {
    var timeLogged = $('.timeElapsed').html();
    var task = {}
    task["taskName"] = taskName;
    task["timeLogged"] = timeLogged;
    task["pomodoroCount"] = 0;
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/timer/task/save",
        data: JSON.stringify(task),
        dataType: 'json',
        timeout: 600000,
        success: function (result) {
            console.log("inside onclick", result);
            $('.timeElapsed').timer('start');
            setTaskList();
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });
}


//Display task list
function setTaskList() {
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/timer/task/list",
        timeout: 600000,
        success: function (result) {
            if (result.length > 0) {
                var taskTableHTML = '';
                $.each(result, function (key, value) {
                    taskTableHTML +=
                        '<tr><td>' + value.taskId + '</td><td>' + value.taskName + '</td><td>' + value.timeLogged + '</td></tr>';
                });
                $("#bodytable").html(taskTableHTML);
            }
            console.log("pomodoro list", result);
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });
}

