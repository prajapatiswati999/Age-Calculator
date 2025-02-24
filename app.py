from flask import Flask, render_template, request
from datetime import datetime, timedelta

app = Flask(__name__)

def calculate_age(dob, target_date):
    """Calculate detailed age breakdown from DOB to target date."""
    dob_date = datetime.strptime(dob, '%Y-%m-%d')
    target_date = datetime.strptime(target_date, '%Y-%m-%d')

    # Calculate total difference in days
    delta = target_date - dob_date
    total_days = delta.days

    # Calculate years, months, and days
    years = target_date.year - dob_date.year
    months = target_date.month - dob_date.month
    days = target_date.day - dob_date.day

    # Adjust for negative days and months
    if days < 0:
        months -= 1
        days += (dob_date.replace(month=dob_date.month + 1, day=1) - timedelta(days=1)).day
    if months < 0:
        years -= 1
        months += 12

    # Calculate total weeks and remaining days
    total_weeks = total_days // 7
    remaining_days = total_days % 7

    # Calculate total hours, minutes, and seconds
    total_hours = total_days * 24
    total_minutes = total_hours * 60
    total_seconds = total_minutes * 60

    return {
        'years': years,
        'months': months,
        'days': days,
        'total_months': years * 12 + months,
        'total_weeks': total_weeks,
        'remaining_days': remaining_days,
        'total_days': total_days,
        'total_hours': total_hours,
        'total_minutes': total_minutes,
        'total_seconds': total_seconds
    }

@app.route('/', methods=['GET', 'POST'])
def index():
    age_details = None
    dob = None
    target_date = datetime.today().strftime('%Y-%m-%d')  # Default target date is today
    error_message = None

    if request.method == 'POST':
        dob = request.form.get('dob')
        target_date = request.form.get('target_date', target_date)  # Use provided target date or default to today

        if dob:
            try:
                dob_date = datetime.strptime(dob, '%Y-%m-%d')
                target_date_obj = datetime.strptime(target_date, '%Y-%m-%d')
                if dob_date > target_date_obj:
                    error_message = "Date of Birth cannot be after the target date."
                else:
                    age_details = calculate_age(dob, target_date)
            except ValueError:
                error_message = "Invalid date format. Please use YYYY-MM-DD."

    return render_template('index.html', age_details=age_details, dob=dob, target_date=target_date, error_message=error_message)

if __name__ == '__main__':
    app.run(port=5001, debug=True)
