$(function () {

    // zadanie 1
    var $section = $('section.task_1');
    var $button = $section.find('button');
    $button.on('click', function () {
        var $div = $('<div class="panel"></div>');
        var $p = $('<p>dowolny test</p>');
        $p.addClass('hide');
        $div.append($p);

        $section.append($div);

    });
    $section.on('mouseenter mouseleave', "div.panel", function () {
        console.log(this);
        $(this).find('p').toggleClass('hide');
    })


    // zadanie 2
    var $sectionPeople = $('section.people');
    var $people = $sectionPeople.find('li');
    var $submit = $sectionPeople.find('[type="submit"]').first();
    var $ul = $sectionPeople.find('.main');

    $people.each(function () {
        deleteButton($(this));
    });

    $ul.on('click', 'input', function () {
        var $liParent = $(this).parent();
        $liParent.remove();
    });

    $submit.click(function (event) {
        event.preventDefault();
        var user = $('#addUser').val();
        console.log(user);
        var age = $('#age').val();
        var $li = $("<li data-age='" + age + "'>");
        $li.append(user);
        console.log($li);
        deleteButton($li);
        $ul.append($li);
    });

    function deleteButton($li) {
        var $deleteBtn =  $("<input>", {
            type: 'submit',
            value: 'usuń'
        });

        $li.append($deleteBtn);
    }

    // nie działa!!!!!!!!!!!!!!!!!!!


    var $section1 = $('section.task_1');
    var $section2 = $('<section class="task_2"><button>sortuj</button></section>');
    $section1.before($section2);

    var $sortButton = $('section.task_2');
    $sortButton.on('click', function () {

    })
    // nie dokończone!!!!!!!!!!!!!!1

});
