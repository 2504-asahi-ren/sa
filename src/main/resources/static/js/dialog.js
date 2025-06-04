$(function() {
    $('.delete-action').on('click', function() {
        if (confirm('削除してもよろしいですか？')) {
    	    return true;
        } else {
            return false;
        }
    });

    $('.change-action').on('click', function() {
        if (confirm('変更してもよろしいですか？')) {
            return true;
        } else {
            return false;
        }
    });
});