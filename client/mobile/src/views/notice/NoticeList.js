// import classes from "./MeetupList.module.css";
import NoticeItem from "views/notice/NoticeItem";
import {Alert,
  UncontrolledAlert,} from "reactstrap";
function NoticeList(props) {
  return (
    <div>
      {props.notices.map((notice) => (
        

      <UncontrolledAlert color="info">
        <NoticeItem
          key={notice.id}
          id={notice.id}
          noticeTitle={notice.noticeTitle}
          noticeContent={notice.noticeContent}
          
        />
      </UncontrolledAlert>

        
      ))}
    </div>
  );
}
export default NoticeList;



