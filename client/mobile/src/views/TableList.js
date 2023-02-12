import React, { useRef } from "react";
import { useDispatch } from "react-redux";
import emailjs from "@emailjs/browser";
// reactstrap components
import classes from "./NoticeWrite.module.css";

import {
  Card,
  CardHeader,
  CardBody,
  CardTitle,
  Table,
  Row,
  Col,
} from "reactstrap";

function Tables() {
  const form = useRef();

  const sendEmail = (e) => {
    e.preventDefault();

    emailjs
      .sendForm(
        "service_0x9vq6a",
        "template_daz3hhq",
        form.current,
        "s8h3MJKjM8o8y_z-B"
      )
      .then(
        (result) => {
          alert("답변은 가입할 때 입력하신 이메일로 받으실 수 있습니다. 문의해주셔서 감사합니다. ")
          console.log(result.text);
        },
        (error) => {
          console.log(error.text);
        }
      );
  };
  return (
    <>
      <div className="content">
        <Row>
          <Col md="12">
            <Card>
              <CardHeader>
                <CardTitle tag="h4">고객센터</CardTitle>
              </CardHeader>
              <CardBody>
                <form ref={form} onSubmit={sendEmail}>
                  <Col>
                    <Row>
                      <label>제목</label>
                    </Row>
                    <Row>
                      <textarea
                        placeholder="제목을 입력해주세요."
                        type="text"
                        name="title"
                        className={classes.titlestyle}
                      />
                    </Row>

                    <Row>
                      <input
                        type="text"
                        name="from_name"
                        defaultValue={`${localStorage.getItem("accountNo")}`}
                        hidden={true}
                      />
                    </Row>

                    <Row>
                      <label>문의사항</label>
                    </Row>
                    <Row>
                      <textarea
                        //   defaultValue="Mike"
                        placeholder="내용을 입력해주세요"
                        name="message"
                        type="text"
                        className={classes.contentstyle}
                        required
                        id="content"
                      />
                    </Row>
                    <Row>
                    <input
                      type="submit"
                      value="문의하기"
                      className="btn-login"
                      // color="black"
                      style={{
                        marginLeft: 15,
                      }}
                    />
                    </Row>
                  </Col>
                </form>
              </CardBody>
            </Card>
          </Col>

          {/* <Col md="12">
            <Card className="card-plain">
              <CardHeader>
                <CardTitle tag="h4">Table on Plain Background</CardTitle>
                <p className="category">Here is a subtitle for this table</p>
              </CardHeader>
              <CardBody>
                <Table className="tablesorter" responsive>
                  <thead className="text-primary">
                    <tr>
                      <th>Name</th>
                      <th>Country</th>
                      <th>City</th>
                      <th className="text-center">Salary</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <td>Dakota Rice</td>
                      <td>Niger</td>
                      <td>Oud-Turnhout</td>
                      <td className="text-center">$36,738</td>
                    </tr>
                    <tr>
                      <td>Minerva Hooper</td>
                      <td>Curaçao</td>
                      <td>Sinaai-Waas</td>
                      <td className="text-center">$23,789</td>
                    </tr>
                    <tr>
                      <td>Sage Rodriguez</td>
                      <td>Netherlands</td>
                      <td>Baileux</td>
                      <td className="text-center">$56,142</td>
                    </tr>
                    <tr>
                      <td>Philip Chaney</td>
                      <td>Korea, South</td>
                      <td>Overland Park</td>
                      <td className="text-center">$38,735</td>
                    </tr>
                    <tr>
                      <td>Doris Greene</td>
                      <td>Malawi</td>
                      <td>Feldkirchen in Kärnten</td>
                      <td className="text-center">$63,542</td>
                    </tr>
                    <tr>
                      <td>Mason Porter</td>
                      <td>Chile</td>
                      <td>Gloucester</td>
                      <td className="text-center">$78,615</td>
                    </tr>
                    <tr>
                      <td>Jon Porter</td>
                      <td>Portugal</td>
                      <td>Gloucester</td>
                      <td className="text-center">$98,615</td>
                    </tr>
                  </tbody>
                </Table>
              </CardBody>
            </Card>
          </Col> */}
        </Row>
      </div>
    </>
  );
}

export default Tables;
