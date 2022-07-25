class Response

  def initialize(response)
    self.response = response
  end

  def allowed_headers
    response.headers["allow"].split(/[ \t]*,[ \t]*/)
  end

  def body
    response.body.to_s
  end

  def content_type
    response.headers["Content-Type"]
  end

  def location
    response.headers["location"]
  end

  def status_code
    response.code
  end

  private

  attr_accessor :response

end
