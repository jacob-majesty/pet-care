import { useEffect, useState } from "react";
import { format } from "date-fns";

export const useAlertWithTimeout = (
  initialVisibility = false,
  duration = 10000
) => {
  const [isVisible, setIsVisible] = useState(initialVisibility);

  useEffect(() => {
    let timer;
    if (isVisible) {
      timer = setTimeout(() => {
        setIsVisible(false);
      }, duration);
    }
    return () => clearTimeout(timer);
  }, [isVisible, duration]);

  return [isVisible, setIsVisible];
};




/**
 * Formats the given date and time.
 * @param {Date | string} date - The date to format.
 * @param {Date | string} time - The time to format.
 * @returns {Object} An object containing formatted date and time strings.
 */
  export const dateTimeFormatter = (date, time) => {
  const formattedDate = format(date, "yyyy-MM-dd");
  const formattedTime = format(time, "HH:mm");
  return { formattedDate, formattedTime };
};

