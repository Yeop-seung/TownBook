// import classes from "./MeetupList.module.css";
import NoticeItem from "views/notice/NoticeItem";
import {Alert,
  UncontrolledAlert,} from "reactstrap";
function NoticeList(props) {
  return (
    <div>
      {props.notices.map((notice) => (
        

      <Alert style={{backgroundColor: "#C1B5A9"}}>
        <NoticeItem
          key={notice.id}
          id={notice.id}
          noticeTitle={notice.noticeTitle}
          noticeContent={notice.noticeContent}
          
        />
      </Alert>

        
      ))}
    </div>
  );
}
export default NoticeList;



